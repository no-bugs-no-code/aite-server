package com.example.aiet_server.domain.user.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTokenResponse {
    private String accessToken;
    private String refreshToken;

}
