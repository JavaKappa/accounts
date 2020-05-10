package com.springboot.accounts.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import com.springboot.accounts.converters.LocalDateConverter;
import com.springboot.accounts.validators.ValidAccountNumber;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;

/**
 * Капу пк
 * 07.05.2020
 */
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "account_number")})
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
    @CsvBindByName(column = "ФИО", required = true)
    private String fullName;

    @NotNull(message = "Birthday is cannot be null")
    @Past
    @Column(name = "birthday")
    @CsvCustomBindByName(column = "Дата рождения", converter = LocalDateConverter.class, required = true)
    private LocalDate birthday;
    @NotNull(message = "Account number is cannot be null")
    @Column(name = "account_number")
    @ValidAccountNumber(message = "account number length must be 20 and cannot starts with 0")
    @CsvBindByName(column = "Номер счета", required = true)
    private BigInteger accountNumber;
    @Column(name = "account_budget")
    @CsvBindByName(column = "Остаток по счету", required = true)
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthday=" + birthday +
                ", accountNumber=" + accountNumber +
                ", accountBudget=" + accountBudget +
                '}';
    }
}
