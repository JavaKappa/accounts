package com.springboot.accounts.model;

import com.springboot.accounts.validators.ValidAccountNumber;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Капу пк
 * 07.05.2020
 */
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames ="account_number")})
public class User {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "global_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private BigInteger id;
    @Size(min = 5, max = 200)
    @NotNull(message = "Name cannot be null")
    @NotBlank
    @Column(name = "full_name")
    private String fullName;

    @NotNull(message = "Birthday is cannot be null")
    @Past
    @Column(name = "birthday")
    private LocalDate birthday;
    @NotNull(message = "Account number is cannot be null")
    @Column(name = "account_number")
    @ValidAccountNumber(message = "account number length must be 20 and cannot starts with 0")
    private BigInteger accountNumber;
    @Column(name = "account_budget")
    private BigDecimal accountBudget;

    public User(String fullName, LocalDate birthday, BigInteger accountNumber, BigDecimal accountBudget) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.accountNumber = accountNumber;
        this.accountBudget = accountBudget;
    }

    public User() {
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
