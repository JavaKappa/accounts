package com.springboot.accounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadFileContents extends RuntimeException {

    public BadFileContents(String message) {
        super(message);
    }
}
