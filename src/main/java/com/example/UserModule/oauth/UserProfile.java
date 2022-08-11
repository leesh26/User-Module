package com.example.UserModule.oauth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class UserProfile {
    private final String oauthId;
    private final String name;
    private final String email;
}
