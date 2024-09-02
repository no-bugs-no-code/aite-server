package com.example.aiet_server.domain.chatGpt.controller;

import com.example.aiet_server.domain.chatGpt.service.ChatGPTImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/chatGpt")
public class ChatGPTImageController {

    @Autowired
    private ChatGPTImageService chatGPTImageService;

    @PostMapping("/analyze-image")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String response = chatGPTImageService.analyzeImage(file);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to analyze image");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
