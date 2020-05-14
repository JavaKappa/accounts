package com.springboot.accounts.service;

import com.springboot.accounts.converters.CsvToUsersConverter;
import com.springboot.accounts.exceptions.ApiRequestException;
import com.springboot.accounts.model.User;
import com.springboot.accounts.repository.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
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
    private CsvToUsersConverter csvToUsersConverter;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> importUsers(MultipartFile file) {
        List<User> users = csvToUsersConverter.readCsvFile(file);
        return repository.saveAll(users);

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
}
