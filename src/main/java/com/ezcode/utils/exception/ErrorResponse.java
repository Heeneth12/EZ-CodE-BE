package com.ezcode.utils.exception;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    private int statusCode;

    public ErrorResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
