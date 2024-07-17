package com.example.aiet_server.domain.chatGpt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatGPTImageService {

    @Value("${openai.secret-key}")
    private String openaiApiKey;

    @Value("${openai.model}")
    private String openaiModel;

    private final RestTemplate restTemplate;
    private final Gson gson;

    public ChatGPTImageService() {
        this.restTemplate = new RestTemplate();
        this.gson = new Gson();
    }

    public String analyzeImage(String imageUrl, String userMessage) {
        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        Map<String, Object> imageMessage = new HashMap<>();
        imageMessage.put("role", "user");
        imageMessage.put("content", imageUrl);

        Map<String, Object> textMessage = new HashMap<>();
        textMessage.put("role", "user");
        textMessage.put("content", userMessage);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", openaiModel);
        requestBody.put("messages", new Object[]{textMessage, imageMessage});
        requestBody.put("max_tokens", 1000);

        HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(requestBody), headers);

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            return handleClientError(e);
        } catch (HttpServerErrorException e) {
            return handleServerError(e);
        } catch (Exception e) {
            return handleGeneralError(e);
        }
    }

    private String handleClientError(HttpClientErrorException e) {
        return "Client error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString();
    }

    private String handleServerError(HttpServerErrorException e) {
        return "Server error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString();
    }

    private String handleGeneralError(Exception e) {
        return "An error occurred: " + e.getMessage();
    }
}
