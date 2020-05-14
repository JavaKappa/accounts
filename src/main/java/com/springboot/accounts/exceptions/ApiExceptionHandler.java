package com.springboot.accounts.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        e.printStackTrace();
        HttpStatus status;
        if (e.getMessage().startsWith("Not found user")) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        ApiException apiException = new ApiException(e.getMessage(),
                e.getClass().getSimpleName(),
                status,
                LocalDateTime.now());
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityException(DataIntegrityViolationException e) {
        e.printStackTrace();
        ApiException apiException = new ApiException(getRootCause(e).getMessage(),
                e.getClass().getSimpleName(),
                HttpStatus.CONFLICT,
                LocalDateTime.now());
        return new ResponseEntity<>(apiException, apiException.getStatus());

    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleConstraintViolationException(NumberFormatException e) {
        e.printStackTrace();
        ApiException apiException = new ApiException("It is must be digit ",
                e.getClass().getSimpleName(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now());
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }

    @ExceptionHandler({Exception.class})
    @Order(Ordered.LOWEST_PRECEDENCE)
    public ResponseEntity<Object> handleConstraintViolation(Exception e) {
        e.printStackTrace();
        String message;
        if (getRootCause(e).getClass().getSimpleName().equals("ConstraintViolationException")) {

            message = "Invalid account number must be contains 20 digits and dont start with 0";
        } else {
            message = e.getMessage();
        }
        ApiException apiException = new ApiException(message,
                e.getClass().getSimpleName(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Object> handleDateTimeParseException(DateTimeParseException e) {
        e.printStackTrace();
        ApiException apiException = new ApiException("It is incorrect date format must be yyyy-mm-dd",
                e.getClass().getSimpleName(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
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