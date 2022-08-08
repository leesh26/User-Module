package com.example.UserModule.oauth;

//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;

import java.util.Map;

//@Service
//public class OAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        return null;
//    }
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2UserService delegate = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = delegate.loadUser(userRequest); //유저정보 가져오기
//
//        String registeredId = userRequest.getClientRegistration().getRegistrationId(); //구글인지 카카오인지
//        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
//        Map<String, Object> attributes = oAuth2User.getAttributes(); //유저 정보(이름, 이메일 등) 저장
//
//        UserProfile userProfile = OAuthAttributes.extract(registrationId, attributes);
//        return null;
//    }
//}
