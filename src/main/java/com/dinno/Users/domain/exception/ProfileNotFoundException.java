package com.dinno.Users.domain.exception;

public class ProfileNotFoundException extends ResourceNotFoundException {
    public ProfileNotFoundException(String message) {
        super(message);
    }
}
