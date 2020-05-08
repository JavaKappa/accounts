package com.springboot.accounts.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Капу пк
 * 07.05.2020
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Size(min = 5, max = 300)
    @NotBlank(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    private String fullName;

    @NotNull(message = "Birthday is cannot be null")
    @Past
    private LocalDate birthday;
    @NotNull(message = "Account number is cannot be null")
    @Positive
    private BigInteger accountNumber;
    private BigDecimal accountBudget;

    public User(String fullName, LocalDate birthday, BigInteger accountNumber, BigDecimal accountBudget) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.accountNumber = accountNumber;
        this.accountBudget = accountBudget;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public BigInteger getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(BigInteger accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBudget() {
        return accountBudget;
    }

    public void setAccountBudget(BigDecimal accountBudget) {
        this.accountBudget = accountBudget;
    }
}
