package com.springboot.accounts.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Капу пк
 * 09.05.2020
 */
public class ApiException {
    private final String message;
    private final String exceptionClassName;
    private final HttpStatus status;
    private final LocalDateTime localDateTime;

    public ApiException(String message, String exceptionClassName, HttpStatus status, LocalDateTime localDateTime) {
        this.message = message;
        this.exceptionClassName = exceptionClassName;
        this.status = status;
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getExceptionClassName() {
        return exceptionClassName;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
