package com.example.aiet_server.domain.user.service;

import com.example.aiet_server.domain.user.payload.request.UserEditRequest;
import com.example.aiet_server.domain.user.payload.request.UserLoginRequest;
import com.example.aiet_server.domain.user.payload.request.UserRegisterRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<String> register(UserRegisterRequest userRegisterRequest);
    ResponseEntity<String> edit(String id,UserEditRequest userEditRequest);
    ResponseEntity<?> login(UserLoginRequest userLoginRequest);
}
