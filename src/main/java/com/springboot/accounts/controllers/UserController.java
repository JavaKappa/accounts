package com.springboot.accounts.controllers;

import com.springboot.accounts.converter.StringToUserConverter;
import com.springboot.accounts.exceptions.BadFileContents;
import com.springboot.accounts.model.User;
import com.springboot.accounts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Капу пк
 * 07.05.2020
 */
@RestController
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private StringToUserConverter converter;


    @PostMapping(value = "/import",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> csvFileUpload(@RequestParam(value = "file", required = false) MultipartFile file,
                                        RedirectAttributes redirectAttributes) {
        if (file == null || file.isEmpty()) {
            throw new BadFileContents("file is empty");
        }
        List<String> rawDataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        file.getInputStream()))){
            String s;
            while ((s = reader.readLine()) != null) {
                if (s.length() == 0){
                    continue;
                }
                rawDataList.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<User> users = convertToListUser(rawDataList);

        return new ResponseEntity<>(service.saveAll(users), HttpStatus.CREATED);
    }

    private List<User> convertToListUser(List<String> rawDataList) {
        List<User> users = new ArrayList<>();
        rawDataList.forEach(u -> users.add(converter.convert(u)));
        return users;
    }
}
