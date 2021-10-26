package com.example.alertme.api;

public class ErrorResponse extends Response {
    public ErrorResponse(String message, int errorCode) {
        super("error", null, message, 400, errorCode);
    }
}
