package com.springboot.accounts.controllers;

import com.springboot.accounts.model.User;
import org.apache.catalina.connector.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Капу пк
 * 07.05.2020
 */
@RestController
public class UserController {
    @GetMapping("/")
    public String sayHello() {
        return "Users";
    }

    @PostMapping("/import")
    public ResponseEntity<User> importEndPoint() {

        return null;
    }
}
