package com.springboot.accounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "wrong csv data format")
public class BadFileContens extends RuntimeException {

    public BadFileContens(String message) {
        super(message);
    }
}
