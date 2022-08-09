package com.example.UserModule.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@Entity
@Getter @Setter
@NoArgsConstructor
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    private Status status;
    private String role;

    private String mailKey;  //인증키가 저장될 컬럼

    @Builder
    public User(Long id, String email, String password, String name, String phone, String nickname, Status status, String role, String mailKey) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
        this.status = status;
        this.role = role;
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
}
