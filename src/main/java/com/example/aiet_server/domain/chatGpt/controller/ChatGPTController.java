package com.example.aiet_server.domain.chatGpt.controller;

import com.example.aiet_server.domain.chatGpt.payload.request.CompletionRequestDto;
import com.example.aiet_server.domain.chatGpt.service.ChatGPTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * ChatGPT API
 */
@RestController
@RequestMapping(value = "/api/v1/chatGpt")
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    public ChatGPTController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @GetMapping("/modelList")
    public ResponseEntity<List<Map<String, Object>>> selectModelList() {
        List<Map<String, Object>> result = chatGPTService.modelList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/model")
    public ResponseEntity<Map<String, Object>> isValidModel(@RequestParam(name = "modelName") String modelName) {
        Map<String, Object> result = chatGPTService.isValidModel(modelName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/prompt")
    public CompletableFuture<ResponseEntity<Map<String, Object>>> selectPrompt(@RequestBody CompletionRequestDto completionRequestDto) {
        return chatGPTService.prompt(completionRequestDto)
                .thenApply(result -> new ResponseEntity<>(result, HttpStatus.OK));
    }
}
