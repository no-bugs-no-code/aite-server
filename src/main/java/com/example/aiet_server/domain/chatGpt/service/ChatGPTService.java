package com.example.aiet_server.domain.chatGpt.service;

import com.example.aiet_server.domain.chatGpt.payload.request.CompletionRequestDto;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface ChatGPTService {

    List<Map<String, Object>> modelList();

    CompletableFuture<Map<String, Object>> prompt(CompletionRequestDto completionRequestDto);

    Map<String, Object> isValidModel(String modelName);
}
