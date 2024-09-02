package com.example.aiet_server.domain.chatGpt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGPTImageService {

    @Value("${openai.secret-key}")
    private String openaiApiKey;

    private final RestTemplate restTemplate;
    private final Gson gson;

    public ChatGPTImageService() {
        this.restTemplate = new RestTemplate();
        this.gson = new Gson();
    }

    public String analyzeImage(MultipartFile file) throws IOException {
        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        // 이미지 파일을 Base64 문자열로 변환
        String base64Image = Base64.getEncoder().encodeToString(file.getBytes());

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4-turbo");
        requestBody.put("messages", List.of(
                Map.of("role", "user", "content", "이거 냉장고 이미지인데, 안에 있는 제품들을 분석해줘 예) 감자 사진 있을 시, 감자라고 나오게"),
                Map.of("role", "user", "content", "data:image/png;base64," + base64Image)
        ));
        requestBody.put("max_tokens", 1000);

        HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(requestBody), headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            return "죄송합니다. 제가 텍스트 입력을 통해 이미지를 분석할 수는 없습니다. 이미지 파일이나 링크를 제공하시면 분석을 도와드릴 수 있습니다. 이미지를 올리신 후 다시 요청해주세요.";
        }
    }
}
