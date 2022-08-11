package com.example.UserModule.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 회원 개인 정보
    private String email;
    private String password;
    private String name;
    private String phone;
    private String nickname;

    // 회원 상태
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    // 인증을 위한 컬럼
    private String mailKey;  //인증키가 저장될 컬럼
    private String oauthId;

    @Builder
    public User(Long id, String email, String password, String name, String phone, String nickname, Status status, String mailKey) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
        this.status = status;
        this.mailKey = mailKey;
    }

    public enum Status {
        ACTIVE("활성회원"),
        DELETE("탈퇴회원"),
        INACTIVE("비활성회원");

        @Getter
        private String message;
        Status(String message) {
            this.message = message;
        }
    }

    public enum Role {
        COMMON("ROLE_USER"),
        ADMIN("ROLE_ADMIN");

        @Getter
        private String message;
        Role(String message) {
            this.message = message;
        }
    }
}
