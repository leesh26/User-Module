package com.example.UserModule.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final OAuthService oAuthService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.headers().frameOptions().disable();
//        http.apply(new CustomDsl());

        http.oauth2Login() // OAuth 로그인 설정 시작!
                .userInfoEndpoint() //로그인 성공 이후 사용자 정보를 가져올 때의 설정
                .userService(oAuthService); // Authentication 객체 생성에 필요한 유저 정보를 반환하는 클래스 설정
//                .and()
//                .successHandler(configSuccessHandler()) //인증을 성공적으로 마칠 경우 처리
//                .failureHandler(configFailureHandler()) //인증 실패할 경우 처리
//                .permitAll();

        return http.build();
    }

//    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {
//        @Override
//        public void configure(HttpSecurity builder) throws Exception {
//            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
//            builder
//                    .addFilterAfter(jwtAuthenticationFilter, LogoutFilter.class);
//        }
//    }
}
