package com.springboot.accounts.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Капу пк
 * 09.05.2020
 */
public class ApiException {
    public final String message;
    public final String exceptionClassName;
    public final HttpStatus status;
    public final LocalDateTime localDateTime;

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
