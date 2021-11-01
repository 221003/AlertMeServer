package com.example.alertme.api.responses;

public class SuccessResponse extends Response {

    public SuccessResponse(Object data) {
        super("success", data, "", 200, 0);
    }
}
