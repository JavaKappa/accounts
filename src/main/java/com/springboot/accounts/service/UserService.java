package com.springboot.accounts.service;

import com.springboot.accounts.exceptions.ApiRequestException;
import com.springboot.accounts.model.User;
import com.springboot.accounts.repository.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Капу пк
 * 07.05.2020
 */
@Service
public class UserService {
    @Autowired
    private CrudUserRepository repository;

    public User save(User user) {
        return repository.save(user);
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


}
