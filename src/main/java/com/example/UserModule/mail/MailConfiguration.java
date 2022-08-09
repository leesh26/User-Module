package com.example.UserModule.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfiguration {
    @Bean
    public MailSendable mailSendable(){
        return new SimpleMail();
    }
}
