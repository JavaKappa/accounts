package com.springboot.accounts.converter;

import com.springboot.accounts.model.User;

public interface StringToUserConverter {
    User convert(String rawData);
}
