package com.springboot.accounts.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        HttpStatus status;
        if (e.getMessage().startsWith("Not found user")) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        ApiException apiException = new ApiException(e.getMessage(),
                e,
                status,
                LocalDateTime.now());
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityException(DataIntegrityViolationException e) {
        ApiException apiException = new ApiException(getRootCause(e).getMessage(),
                e,
                HttpStatus.CONFLICT,
                LocalDateTime.now());
        return new ResponseEntity<>(apiException, apiException.getStatus());

    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleConstraintViolationException(NumberFormatException e) {
        ApiException apiException = new ApiException(getRootCause(e).getMessage(),
                e,
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now());
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }

    public Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }
}