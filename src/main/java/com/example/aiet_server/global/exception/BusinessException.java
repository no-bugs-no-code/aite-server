package com.example.aiet_server.global.exception;

import com.example.aiet_server.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {
    private final ErrorProperty error;
}