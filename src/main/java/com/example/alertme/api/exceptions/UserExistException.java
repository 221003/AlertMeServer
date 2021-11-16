package com.example.alertme.api.exceptions;

public class UserExistException extends RuntimeException {
    private final int errorCode = 12;

    public UserExistException(String text) {
        super("User exist: " + text);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
