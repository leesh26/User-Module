package com.example.UserModule.service;

import com.example.UserModule.dto.SmsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SmsService {

    @Value("${sens.serviceId}")
    private String serviceId;

    @Value("${sens.secretKey}")
    private String sKey;

    @Value("${sens.accessKey}")
    private String aKey;

    @Value("${sens.from}")
    private String from;

    public SmsDto.SendSmsResponseDto send(String number, String randNum) throws JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        Long time = System.currentTimeMillis();
        List<SmsDto.MessageRequestDto> messages = new ArrayList<>();
        messages.add(new SmsDto.MessageRequestDto(number, "인증번호는" + randNum+ "입니다. 정확히 입력해주세요."));

        SmsDto.SmsRequestDto smsRequestDto = SmsDto.SmsRequestDto.builder()
                .from(from)
                .type("SMS")
                .content(null)
                .messages(messages)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(smsRequestDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", aKey);
        headers.set("x-ncp-apigw-signature-v2", makeSignature(time));

        HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://sens.apigw.ntruss.com/sms/v2/services/" + serviceId + "/messages";
        SmsDto.SendSmsResponseDto sendSmsResponseDto = restTemplate.postForObject(url, body, SmsDto.SendSmsResponseDto.class);

        // reddis에 전송한 값 정보 저장

        return sendSmsResponseDto;
    }

    public String makeSignature(Long time) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        String space = " ";					// one space
        String newLine = "\n";					// new line
        String method = "POST";					// method
        String url = "/sms/v2/services/" + serviceId + "/messages";	// url (include query string)
        String timestamp = time.toString();			// current timestamp (epoch)
        String accessKey = aKey;			// access key id (from portal or Sub Account)
        String secretKey = sKey;

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }

//    public boolean checkVerify(String input){
//
//    }
}
