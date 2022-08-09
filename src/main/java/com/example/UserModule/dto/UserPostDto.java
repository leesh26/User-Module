package com.example.UserModule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPostDto {
    String email;
    String password;
    String name;
    String phone;
    String nickname;
}
