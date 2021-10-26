package com.example.alertme.api.exceptions;

public class UserNotFoundException extends RuntimeException  {

    private final int errorCode = 11;

    public UserNotFoundException(String text) {
        super("Could not find user: " + text);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
