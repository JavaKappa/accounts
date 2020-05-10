package com.springboot.accounts.controllers;

import com.springboot.accounts.model.User;
import com.springboot.accounts.service.UserService;
import com.springboot.accounts.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;

/**
 * Капу пк
 * 07.05.2020
 */
@RestController
public class UserController {
    @Autowired
    private UserService service;


    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") BigInteger id) {
        return service.getUser(id);
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    @PostMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> csvFileUpload(@RequestParam(value = "file", required = false) MultipartFile file) {
        List<User> users = service.importUsers(file);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }


}
