package com.springboot.accounts.converter;

import com.springboot.accounts.exceptions.BadFileContents;
import com.springboot.accounts.model.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class StringUserConverterImpl implements StringToUserConverter {
    @Override
    public User convert(String rawData) {
        String[] params = rawData.split(";");
        if (params.length != 4) {
            throw new BadFileContents(rawData + " is incorrect users not saved");
        }
        String fullName = params[0];
        LocalDate birthday = validateBirthday(params[1], rawData);
        BigInteger accountNumber = validateAccountNumber(params[2], rawData);
        BigDecimal accountBudget = validateAccountBudget(params[3], rawData);

        return new User(fullName, birthday, accountNumber.toString(), accountBudget);
    }

    private LocalDate validateBirthday(String date, String rawData) {
        try {
           return LocalDate.parse(date);
        } catch (DateTimeParseException exception) {
            throw new BadFileContents(rawData + " is incorrect birthday format. Users not saved");
        }
    }

    private BigInteger validateAccountNumber(String accountNumber, String rawData) {
        if (accountNumber.length() != 20 || accountNumber.startsWith("0")) {
            throw new BadFileContents(String.format("%s is incorrect size of Account number", rawData));
        }
        try {
            return new BigInteger(accountNumber);
        } catch (Exception e) {
            throw new BadFileContents(String.format("%s is incorrect Account number", rawData));
        }
    }

    private BigDecimal validateAccountBudget(String accountBudget, String rawData) {
        try {
            return new BigDecimal(accountBudget);
        } catch (Exception e) {
            throw new BadFileContents(String.format("%s is incorrect account budget", rawData));
        }
    }
}
