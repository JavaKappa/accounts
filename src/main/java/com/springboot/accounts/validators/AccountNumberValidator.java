package com.springboot.accounts.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigInteger;

/**
 * Капу пк
 * 09.05.2020
 */
public class AccountNumberValidator implements ConstraintValidator<ValidAccountNumber, BigInteger> {
    @Override
    public void initialize(ValidAccountNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(BigInteger value, ConstraintValidatorContext context) {
        return value.toString().length() == 20;
    }
}
