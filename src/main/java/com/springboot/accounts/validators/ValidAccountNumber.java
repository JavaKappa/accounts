package com.springboot.accounts.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Капу пк
 * 09.05.2020
 */
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = {AccountNumberValidator.class})
public @interface ValidAccountNumber {
    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
