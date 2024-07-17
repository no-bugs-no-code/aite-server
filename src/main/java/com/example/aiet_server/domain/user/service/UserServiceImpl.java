package com.example.aiet_server.domain.user.service;

import ch.qos.logback.core.spi.ErrorCodes;
import com.example.aiet_server.domain.user.entity.UserEntity;
import com.example.aiet_server.domain.user.payload.request.UserEditRequest;
import com.example.aiet_server.domain.user.payload.request.UserLoginRequest;
import com.example.aiet_server.domain.user.payload.request.UserRegisterRequest;
import com.example.aiet_server.domain.user.payload.response.UserTokenResponse;
import com.example.aiet_server.domain.user.repository.UserRepository;
import com.example.aiet_server.global.exception.BusinessException;
import com.example.aiet_server.global.exception.error.ErrorCode;
import com.example.aiet_server.global.interceptor.jwt.JwtUtil;
import jdk.jshell.execution.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> register(UserRegisterRequest request) {
        UserEntity entity = request.toUser();
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepository.save(entity);
        return new ResponseEntity<>("create", HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<String> edit(String id, UserEditRequest request) {
        UserEntity user = userRepository.findById(id);
        user.setName(request.getName());
        user.setGender(request.getGender());
        user.setBirth(request.getBirth());
        user.setHeight(request.getHeight());
        user.setWeight(request.getWeight());
        userRepository.save(user);
        return new ResponseEntity<>("update",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> login(UserLoginRequest userLoginRequest) {
        UserEntity user = userRepository.findById(userLoginRequest.getId());
        if (! passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword() )){
            throw new BusinessException(ErrorCode.PASSWORD_ERROR);
        }
        return new ResponseEntity<>(new UserTokenResponse(jwtUtil.generateAccessToken(userLoginRequest.getId()),jwtUtil.generateRefreshToken(userLoginRequest.getId())),HttpStatus.OK);
    }
}
