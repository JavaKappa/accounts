package com.springboot.accounts.converters;

import com.springboot.accounts.exceptions.ApiRequestException;
import com.springboot.accounts.model.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Component
public class StringUserConverterImpl implements StringToUserConverter {
    @Override
    public User convert(String rawData) {
        String[] params = rawData.split(";");
        if (params.length != 4) {
            throw new ApiRequestException(rawData + " is incorrect users not saved");
        }
        String fullName = params[0].trim();
        LocalDate birthday = LocalDate.parse(params[1].trim());
        BigInteger accountNumber = new BigInteger(params[2].trim());
        BigDecimal accountBudget = validateAccountBudget(params[3].trim(), rawData);

        return new User(fullName, birthday, accountNumber, accountBudget);
    }


    private BigDecimal validateAccountBudget(String accountBudget, String rawData) {
        try {
            return new BigDecimal(accountBudget);
        } catch (Exception e) {
            throw new ApiRequestException(String.format("%s account number must be a digit", rawData));
        }
    }
}
