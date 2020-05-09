package com.springboot.accounts.converters;

import com.springboot.accounts.model.User;

public interface StringToUserConverter {
    User convert(String rawData);
}
