package com.springboot.accounts.controllers;

import com.springboot.accounts.converter.StringToUserConverter;
import com.springboot.accounts.model.User;
import com.springboot.accounts.service.UserService;
import com.sun.tools.jdeprscan.CSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
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


    @PostMapping(value = "/import")
    public String csvFileUpload(@RequestParam("file") MultipartFile file,
                                RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<String> rawDataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        file.getInputStream()))){
            String s;
            while ((s = reader.readLine()) != null) {
                rawDataList.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<User> users = convertToListUser(rawDataList);
        return users.toString();
    }

    private List<User> convertToListUser(List<String> rawDataList) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < rawDataList.size(); i++) {
            users.add(converter.convert(rawDataList.get(i)));
        }

        return users;
    }
}
