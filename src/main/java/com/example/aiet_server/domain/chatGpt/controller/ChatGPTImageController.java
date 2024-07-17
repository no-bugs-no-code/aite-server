package com.example.aiet_server.domain.chatGpt.controller;

import com.example.aiet_server.domain.chatGpt.service.ChatGPTImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/api/v1/chatGpt")
public class ChatGPTImageController {

    @Autowired
    private ChatGPTImageService chatGPTImageService;

    @PostMapping("/analyze-url")
    @ResponseBody
    public ResponseEntity<String> handleImageUrl(@RequestBody Map<String, String> body) {
        String imageUrl = body.get("url");
        String userMessage = "이거 냉장고 이미지인데, 안에 있는 제품들을 분석해줘 예) 감자 사진 있을 시, 감자라고 나오게";

        try {
            String response = chatGPTImageService.analyzeImage(imageUrl, userMessage);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
