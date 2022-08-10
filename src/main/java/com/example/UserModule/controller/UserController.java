package com.example.UserModule.controller;

import com.example.UserModule.dto.UserMapper;
import com.example.UserModule.dto.UserPostDto;
import com.example.UserModule.entity.User;
import com.example.UserModule.mail.MailSendable;
import com.example.UserModule.service.SmsService;
import com.example.UserModule.service.UserService;
import com.example.UserModule.util.MakeRandNum;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;
    private final SmsService smsService;
    private final MailSendable mailSendable;
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserPostDto userPostDto) throws InterruptedException {
        User user = userMapper.UserPostDtoToUser(userPostDto);

        // 메일 전송
        String randKey = mailSendable.send(user.getEmail(), MakeRandNum.getNum());

        // DB에 상태 저장
        user.setMailKey(randKey);
        user.setStatus(User.Status.INACTIVE);
        user.setRole("ROLE_USER");

        userService.saveUser(user);

        return new ResponseEntity<>(user.getName(), HttpStatus.CREATED);
    }

    @GetMapping("/phoneAuth")
    public ResponseEntity phoneAuth(@RequestParam String phone) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        smsService.send(phone,  MakeRandNum.getNum());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/emailAuth")
    public @ResponseBody String emailAuth(@RequestParam String email, @RequestParam String key) {
        User user = userService.findUser(email).orElseThrow(() -> new RuntimeException("일치하는 회원이 없다."));
        if (user.getMailKey().equals(key)){
            user.setMailKey(null);
            user.setStatus(User.Status.ACTIVE);
            userService.saveUser(user);
            return "인증완료";
        }
        else return "인증실패";
    }


}
