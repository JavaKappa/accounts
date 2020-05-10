package com.springboot.accounts.converters;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.springboot.accounts.exceptions.ApiRequestException;
import com.springboot.accounts.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * Капу пк
 * 10.05.2020
 */
@Component
public class CsvToUsersConverter {
    public List<User> readCsvFile(MultipartFile file) throws FileNotFoundException {
        Reader r = null;
        try {
            r = new BufferedReader(new InputStreamReader(file.getInputStream()));
        } catch (IOException e) {
            throw new ApiRequestException("something wrong with file ", e);
        }
        HeaderColumnNameMappingStrategy<User> ms = new HeaderColumnNameMappingStrategy<>();
        ms.setType(User.class);

        CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(r)
                .withSeparator(';')
                .withMappingStrategy(ms)
                .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                .build();

        System.out.println(csvToBean.parse());
        return csvToBean.parse();
    }
}
