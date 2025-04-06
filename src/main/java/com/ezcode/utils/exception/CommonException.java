package com.ezcode.utils.exception;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final int statusCode;

    public CommonException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public CommonException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

}
