package com.example.UserModule.controller;

import com.example.UserModule.dto.UserMapper;
import com.example.UserModule.dto.UserPostDto;
import com.example.UserModule.entity.User;
import com.example.UserModule.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;
    private final SmsService smsService;

//    @PostMapping("/join")
//    public ResponseEntity<String> join(@RequestBody UserPostDto userPostDto){
//        User user = userMapper.UserPostDtoToUser(userPostDto);
////        return ResponseEntity.created();
//    }

    @GetMapping("/phoneAuth")
    public String phoneAuth(@RequestParam String phone) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        Random rand = new Random();
        String randNum = "";
        for (int i = 0; i < 6; i++){
            String randN = Integer.toString(rand.nextInt(10)); // 0과 10사이의 난수 생성
            randNum += randN;
        }

        smsService.send(phone, "인증번호는 " + randNum + "입니다. 정확히 입력해주세요.");
        return "인증번호 발송 완료!";
    }

    @PostMapping("/phoneAuth")
    public Boolean phoneAuthIsOk(@RequestBody String input){
        return smsService.checkVerify(input);
    }

}
