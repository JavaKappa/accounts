package com.springboot.accounts.service;

import com.springboot.accounts.model.User;
import com.springboot.accounts.repository.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Капу пк
 * 07.05.2020
 */
@Service
public class UserService {
    @Autowired
    private CrudUserRepository repository;

}
