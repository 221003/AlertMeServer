package com.example.alertme.api.exceptions;

public class AlertNotFoundException extends RuntimeException {
    private final int errorCode = 21;

    public AlertNotFoundException(String text) {
        super("Could not find alert: " + text);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
