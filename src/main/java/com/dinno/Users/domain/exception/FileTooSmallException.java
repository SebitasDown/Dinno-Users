package com.dinno.Users.domain.exception;

public class FileTooSmallException extends RuntimeException {
    public FileTooSmallException(String message) {
        super(message);
    }
}
