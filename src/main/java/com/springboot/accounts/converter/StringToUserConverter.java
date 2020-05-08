package com.springboot.accounts.converter;

import com.springboot.accounts.model.User;
import org.springframework.stereotype.Component;

public interface StringToUserConverter {

    User convert(String rawData);
}
