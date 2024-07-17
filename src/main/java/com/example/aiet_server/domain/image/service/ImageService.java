package com.example.aiet_server.domain.image.service;

import com.example.aiet_server.domain.image.dto.ImageDTO;
import com.example.aiet_server.global.S3.S3Uploader;
import com.example.aiet_server.global.exception.BusinessException;
import com.example.aiet_server.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final S3Uploader s3Uploader;

    public ImageDTO uploadImage(MultipartFile multipartFile) {
        try {
            return new ImageDTO(s3Uploader.upload(multipartFile, "picture"));
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.FILE_ERROR);
        }
    }
}

