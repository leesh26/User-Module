package com.example.UserModule.mail;

import org.springframework.stereotype.Component;

@Component
public interface MailSendable {
    String send(String target, String message) throws InterruptedException;
}
