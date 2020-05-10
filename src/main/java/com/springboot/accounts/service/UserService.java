package com.springboot.accounts.service;

import com.springboot.accounts.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;

/**
 * Капу пк
 * 10.05.2020
 */
public interface UserService {
    List<User> importUsers(MultipartFile file);

    @Transactional
    List<User> saveAll(@NotNull List<User> users);

    User getUser(BigInteger id);

    User save(User user);

    List<User> getAllUsers();

}
