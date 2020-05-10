package com.springboot.accounts.converters;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.LocalDate;

/**
 * Капу пк
 * 10.05.2020
 */
public class LocalDateConverter extends AbstractBeanField<LocalDate> {
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return LocalDate.parse(value);
    }
}
