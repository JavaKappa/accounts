package com.springboot.accounts.service;

import com.springboot.accounts.converters.StringToUserConverter;
import com.springboot.accounts.exceptions.ApiRequestException;
import com.springboot.accounts.model.User;
import com.springboot.accounts.repository.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Капу пк
 * 07.05.2020
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private CrudUserRepository repository;
    @Autowired
    private StringToUserConverter converter;

    public User save(User user) {
        return repository.save(user);
    }

    public List<User> importUsers(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new ApiRequestException("file is empty");
        }
        List<String> rawDataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        file.getInputStream(), Charset.forName("utf-8")))) {
            String s;
            while ((s = reader.readLine()) != null) {
                if (s.length() == 0) {
                    continue;
                }
                rawDataList.add(s);
            }
        } catch (IOException e) {
            throw new ApiRequestException(e.getMessage(), e);
        }

        List<User> users = convertToListUser(rawDataList);
        return saveAll(users);
    }

    @Transactional
    public List<User> saveAll(@NotNull List<User> users) {
        users.forEach(user -> {
            try {
                save(user);
            } catch (DataIntegrityViolationException e) {
                throw new ApiRequestException(String.format("%s account number from %s is already in Database",
                        user.getAccountNumber(), user.getFullName()), e);
            }
        });
        return users;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(BigInteger id) {
        User user = repository.getUserById(id);
        if (user == null) {
            throw new ApiRequestException("Not found user with id " + id);
        }
        return repository.getUserById(id);
    }

    private List<User> convertToListUser(List<String> rawDataList) {
        List<User> users = new ArrayList<>();
        rawDataList.forEach(u -> users.add(converter.convert(u)));
        return users;
    }
}
