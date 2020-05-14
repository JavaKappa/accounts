package com.springboot.accounts.converters;

import com.opencsv.*;
import com.opencsv.bean.*;
import com.springboot.accounts.exceptions.ApiRequestException;
import com.springboot.accounts.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Капу пк
 * 10.05.2020
 */
@Component
public class CsvToUsersConverter {
    private static  CSVParser parser = new CSVParserBuilder()
            .withSeparator(';')
            .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
            .build();


    public List<User> readCsvFile(MultipartFile file) {
        CSVReader reader = null;
        try {
            reader = new CSVReaderBuilder(
                    new BufferedReader(
                            new InputStreamReader(
                                    file.getInputStream(), Charset.forName("Utf-8"))))
                    .withCSVParser(parser)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiRequestException("Something wrong with file", e);
        }
        HeaderColumnNameMappingStrategy<User> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
        mappingStrategy.setType(User.class);

        CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(reader)
                .withMappingStrategy(mappingStrategy)
                .build();
        return csvToBean.parse();
    }
}
