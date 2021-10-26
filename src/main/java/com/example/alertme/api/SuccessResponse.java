package com.example.alertme.api;

public class SuccessResponse extends Response {

    public SuccessResponse(Object data) {
        super("success", data, "", 200, 0);
    }
}
