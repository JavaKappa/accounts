package com.springboot.accounts.exceptions;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable t) {
        super(message, t);
    }
}
