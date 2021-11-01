package com.example.alertme.api.exceptions;

public class AlertTypeNotFoundException extends RuntimeException {
    private final int errorCode = 31;

    public AlertTypeNotFoundException(String text) {
        super("Could not find alert type: " + text);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
