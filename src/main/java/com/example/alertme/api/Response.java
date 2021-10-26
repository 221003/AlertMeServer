package com.example.alertme.api;

public class Response {
    private String status;
    private int statusCode;
    private Object data;
    private String error;
    private int errorCode;

    public Response(String status, Object data, String error, int statusCode, int errorCode) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
