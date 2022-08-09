package com.example.UserModule.util;

import org.springframework.stereotype.Service;

import java.util.Random;

public class MakeRandNum {

    public static String getNum(){
        Random rand = new Random();
        String randNum = "";
        for (int i = 0; i < 6; i++){
            String randN = Integer.toString(rand.nextInt(10)); // 0과 10사이의 난수 생성
            randNum += randN;
        }
        return randNum;
    }
}
