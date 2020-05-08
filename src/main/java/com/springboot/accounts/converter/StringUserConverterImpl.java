package com.springboot.accounts.converter;

import com.springboot.accounts.model.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Component
public class StringUserConverterImpl implements StringToUserConverter{
    @Override
    public User convert(String rawData) {
        String[] params = rawData.split(";");
        if (params.length != 4) {
            throw new IllegalArgumentException();
        }
        String fullName = params[0];
        LocalDate birthday = LocalDate.parse(params[1]);
        BigInteger accountNumber = new BigInteger(params[2]);
        BigDecimal accountBudget = new BigDecimal(params[3]);
        return new User(fullName, birthday, accountNumber, accountBudget);
    }
}
