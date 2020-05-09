package com.springboot.accounts.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Капу пк
 * 09.05.2020
 */
public class ApiException {
    public final String message;
    public final Throwable e;
    public final HttpStatus status;
    public final LocalDateTime localDateTime;

    public ApiException(String message, Throwable e, HttpStatus status, LocalDateTime localDateTime) {
        this.message = message;
        this.e = e;
        this.status = status;
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getE() {
        return e;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
