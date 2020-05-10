package com.springboot.accounts.converters;

import com.opencsv.*;
import com.opencsv.bean.*;
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
    public List<User> readCsvFile(MultipartFile file) {
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                .build();
        CSVReader reader = null;
        try {
            reader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(file.getInputStream()))).withCSVParser(parser).build();
        } catch (IOException e) {
            throw new ApiRequestException("Something wrong with file", e);
        }
        HeaderColumnNameMappingStrategy<User> ms = new HeaderColumnNameMappingStrategy<>();
        ms.setType(User.class);

        CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(reader)
                .withMappingStrategy(ms)
                .build();
        return csvToBean.parse();
    }
}
