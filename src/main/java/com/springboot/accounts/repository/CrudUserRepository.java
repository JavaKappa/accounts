package com.springboot.accounts.repository;

import com.springboot.accounts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * Капу пк
 * 07.05.2020
 */
@Repository
public interface CrudUserRepository extends JpaRepository<User, BigInteger> {

}
