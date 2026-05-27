package com.noticiero.udc.domain.valueobjects;


import com.noticiero.udc.domain.exceptions.InvalidUserNameException;

public class UserName {
    private final String value;

    public UserName(String value) {
        if (value == null || value.trim().length() < 3) {
            throw new InvalidUserNameException("El nombre deber tener almenos 3 caracteres");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
