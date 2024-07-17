package com.example.aiet_server.domain.chatGpt.payload.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 프롬프트 요청 DTO
 *
 * @author : lee
 * @fileName : CompletionRequestDto
 * @since : 12/29/23
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompletionRequestDto {

    private String model;
    private List<Message> messages;
    private float temperature;

    @Builder
    CompletionRequestDto(String model, List<Message> messages, float temperature) {
        this.model = model;
        this.messages = messages;
        this.temperature = temperature;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Message {
        private String role;
        private String content;

        @Builder
        Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}
