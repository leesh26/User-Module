package com.example.UserModule.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleMail implements MailSendable{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String send(String target, String randNum){

        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(target);
        simpleMessage.setSubject("[이소희] 인증링크 입니다.");
        simpleMessage.setText("http://localhost:8080/emailAuth?email=" + target + "&key=" + randNum);
        javaMailSender.send(simpleMessage);
        return randNum;
    }

}
