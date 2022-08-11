package com.example.UserModule.oauth;

import com.example.UserModule.dto.UserPostDto;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public enum OAuthAttributes {
    // id, 이름, 이메일 꺼내기
    GOOGLE("google", (attributes) -> {
        return new UserProfile(String.valueOf(attributes.get("sub")),
                (String) attributes.get("name"),
                (String) attributes.get("email")); });

    private final String registrationId;
    private final Function<Map<String, Object>, UserProfile> of; // Map을 받아서 UserProfile을 리턴하는 함수형 인터페이스

    OAuthAttributes(String registrationId, Function<Map<String, Object>, UserProfile> of) {
        this.registrationId = registrationId;
        this.of = of;
    }

    public static UserProfile extract (String registrationId, Map<String, Object> attributes){
        return Arrays.stream(values())
                .filter(provider -> registrationId.equals(provider.registrationId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of.apply(attributes); // attributes를 입력 -> stream 함수 적용 -> UserProfile 출력
    }
}
