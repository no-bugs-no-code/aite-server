package com.example.aiet_server.global.exception.user;

import com.example.aiet_server.global.exception.error.ErrorProperty;
import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookError implements ErrorProperty {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    IMAGE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "사진 업로드 중 오류가 발생하였습니다.");
    private final HttpStatus status;
    private final String message;
}
