package com.springboot.accounts.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.springboot.accounts.converters.CsvToUsersConverter;
import com.springboot.accounts.converters.StringToUserConverter;
import com.springboot.accounts.exceptions.ApiRequestException;
import com.springboot.accounts.model.User;
import com.springboot.accounts.repository.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Капу пк
 * 07.05.2020
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private CrudUserRepository repository;
    @Autowired
    private StringToUserConverter converter;
    @Autowired
    private CsvToUsersConverter csvToUsersConverter;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> importUsers(MultipartFile file) {
        try {
            List<User> users = csvToUsersConverter.readCsvFile(file);
            return users;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//            FileWriter w = new FileWriter("D:/123/123.csv");
//            StatefulBeanToCsv<User> beanToCsv = new StatefulBeanToCsvBuilder<User>(w)
//            .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//                    .withSeparator(';')
//                    .build();
//            beanToCsv.write(new User("tERminator", LocalDate.now(), new BigInteger("3333"), new BigDecimal("222.22")));
//            beanToCsv.write(new User("tERminator", LocalDate.now(), new BigInteger("3333"), new BigDecimal("222.22")));
//            beanToCsv.write(new User("tERminator", LocalDate.now(), new BigInteger("3333"), new BigDecimal("222.22")));
//            w.close();
//            HeaderColumnNameMappingStrategy<User> ms = new HeaderColumnNameMappingStrategy<>();
//            ms.setType(User.class);
//
//            FileReader r = new FileReader("D:/123/123.csv");
//            CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(new CSVReader(r))
//                    .withType(User.class)
//                    .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
//                    .withSeparator(';')
//                    .build();
//            csvToBean.parse().forEach(u -> System.out.println(u));
//            r.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (CsvRequiredFieldEmptyException e) {
//            e.printStackTrace();
//        } catch (CsvDataTypeMismatchException e) {
//            e.printStackTrace();
//        }

        return null;
//        List<String> rawDataList = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(
//                new InputStreamReader(
//                        file.getInputStream(), Charset.forName("utf-8")))) {
//            String s;
//            while ((s = reader.readLine()) != null) {
//                if (s.length() == 0) {
//                    continue;
//                }
//                rawDataList.add(s);
//            }
//        } catch (IOException e) {
//            throw new ApiRequestException(e.getMessage(), e);
//        }
//
//        List<User> users = convertToListUser(rawDataList);
//        return saveAll(users);
    }

    @Transactional
    public List<User> saveAll(@NotNull List<User> users) {
        users.forEach(user -> {
            try {
                save(user);
            } catch (DataIntegrityViolationException e) {
                throw new ApiRequestException(String.format("%s account number from %s is already in Database",
                        user.getAccountNumber(), user.getFullName()), e);
            }
        });
        return users;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(BigInteger id) {
        User user = repository.getUserById(id);
        if (user == null) {
            throw new ApiRequestException("Not found user with id " + id);
        }
        return repository.getUserById(id);
    }

    private List<User> convertToListUser(List<String> rawDataList) {
        List<User> users = new ArrayList<>();
        rawDataList.forEach(u -> users.add(converter.convert(u)));
        return users;
    }
}
