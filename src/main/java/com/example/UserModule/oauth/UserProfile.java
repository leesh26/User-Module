package com.example.UserModule.oauth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserProfile {
    private final String email;
    private final String name;

}
