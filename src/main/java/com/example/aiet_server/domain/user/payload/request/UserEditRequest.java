package com.example.aiet_server.domain.user.payload.request;

import lombok.Data;

@Data
public class UserEditRequest {
    private int weight;
    private int height;
    private String birth;
    private String name;
    private char gender;
}
