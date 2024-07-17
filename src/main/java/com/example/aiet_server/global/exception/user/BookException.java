package com.example.aiet_server.global.exception.user;

import com.example.aiet_server.global.exception.BusinessException;

public class BookException extends BusinessException {
    private static final BookException USER_NOT_FOUND = new BookException(BookError.USER_NOT_FOUND);
    private static final BookException IMAGE_UPLOAD_FAILED = new BookException(BookError.IMAGE_UPLOAD_FAILED);
    public BookException(BookError error) {
        super(error);
    }

    public static BookException userNotFound() {
        return USER_NOT_FOUND;
    }
    public static BookException imageUploadFailed() {
        return IMAGE_UPLOAD_FAILED;
    }
}
