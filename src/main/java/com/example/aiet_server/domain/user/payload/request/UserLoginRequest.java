package com.example.aiet_server.domain.user.payload.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String id;
    private String password;
}
