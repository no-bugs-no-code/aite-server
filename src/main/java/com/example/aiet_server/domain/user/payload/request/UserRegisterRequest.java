package com.example.aiet_server.domain.user.payload.request;

import com.example.aiet_server.domain.user.entity.UserEntity;
import lombok.Data;

@Data
public class UserRegisterRequest {
    private String name;
    private String id;
    private String password;

    public UserEntity toUser() {
        return new UserEntity(
                id, password, name
        );
    }
}
