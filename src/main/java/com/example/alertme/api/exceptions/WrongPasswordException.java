package com.example.alertme.api.exceptions;

public class WrongPasswordException extends RuntimeException {
    private final int errorCode = 10;

    public WrongPasswordException() {
        super("Wrong password.");
    }

    public int getErrorCode() {
        return errorCode;
    }
}
