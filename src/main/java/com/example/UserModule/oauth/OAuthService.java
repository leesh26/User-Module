package com.example.UserModule.oauth;


import com.example.UserModule.entity.User;
import com.example.UserModule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService(); // access token과 유저 정보 접근 가능
        OAuth2User oAuth2User = delegate.loadUser(userRequest); //유저정보 가져오기

        String registeredId = userRequest.getClientRegistration().getRegistrationId(); //구글인지 카카오인지
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes(); //유저 정보(이름, 이메일 등) 저장

        UserProfile userProfile = OAuthAttributes.extract(registeredId, attributes); // 필요한 정보를 꺼내서 프로필 생성
        System.out.println(userProfile.toString());

        // 가입된 회원이 없으면 회원가입, 있으면 가져오기
        Optional<User> userTemp = userService.findUser(userProfile.getEmail());
        User user;

        // 타 사이트에서 변경이 있는 경우를 따질 것인가
        if (userTemp.isEmpty()){
            // 입력된 값 : 이메일, oauth, name
            user = User.builder()
                    .email(userProfile.getEmail())
                    .name(userProfile.getName())
                    .status(User.Status.ACTIVE)
                    .build();
            user.setRole(User.Role.COMMON);
            user.setOauthId(userProfile.getOauthId());
            userService.saveUser(user);
        }
        else user = userTemp.get();


        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRole().getMessage())),
                attributes, userNameAttributeName);
    }
}
