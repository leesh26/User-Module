package com.example.UserModule.oauth;

//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig {
//    private final OAuthService oAuthService;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//
//        http.oauth2Login()
//                .userInfoEndpoint()
//                .userService(oAuthService);
//
//        return http.build();
//    }
//}
