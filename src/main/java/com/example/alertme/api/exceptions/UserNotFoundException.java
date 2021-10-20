package com.example.alertme.api.exceptions;

public class UserNotFoundException extends RuntimeException  {
    public UserNotFoundException(String text) {
        super("Could not find user: " + text);
    }
}
