package com.example.UserModule.dto;

import lombok.*;

import java.util.List;

public class SmsDto {
    @Data
    public static class SmsRequestDto {
        String type;
        String from;
        String content;
        List<MessageRequestDto> messages;

        @Builder
        public SmsRequestDto(String type, String from, String content, List<MessageRequestDto> messages) {
            this.type = type;
            this.from = from;
            this.content = content;
            this.messages = messages;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SendSmsResponseDto {
        String statusCode;
        String statusName;
        String requestId;
        String requestTime;
    }

    @Data
    @AllArgsConstructor
    public static class MessageRequestDto {
        String to;
        String content;
    }
}
