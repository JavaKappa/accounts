package com.springboot.accounts.converters;

import com.springboot.accounts.exceptions.ApiRequestException;
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
            throw new ApiRequestException(rawData + " is incorrect users not saved");
        }
        String fullName = params[0];
        LocalDate birthday = validateBirthday(params[1].trim(), rawData);
        BigInteger accountNumber = new BigInteger(params[2].trim());
        BigDecimal accountBudget = validateAccountBudget(params[3].trim(), rawData);

        return new User(fullName, birthday, accountNumber, accountBudget);
    }

    private LocalDate validateBirthday(String date, String rawData) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException exception) {
            throw new ApiRequestException(rawData + " is incorrect birthday format. Users not saved");
        }
    }

    private BigInteger validateAccountNumber(String accountNumber, String rawData) {
        if (accountNumber.length() != 20 || accountNumber.startsWith("0")) {
            throw new ApiRequestException(String.format("%s is incorrect size of Account number", rawData));
        }
        try {
            return new BigInteger(accountNumber);
        } catch (NumberFormatException e) {
            throw new ApiRequestException(String.format("%s is incorrect Account number", rawData), e);
        }
    }

    private BigDecimal validateAccountBudget(String accountBudget, String rawData) {
        try {
            return new BigDecimal(accountBudget);
        } catch (Exception e) {
            throw new ApiRequestException(String.format("%s account number must be a digit", rawData));
        }
    }
}
