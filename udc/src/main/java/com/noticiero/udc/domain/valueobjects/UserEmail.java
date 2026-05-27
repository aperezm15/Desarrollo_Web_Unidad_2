package com.noticiero.udc.domain.valueobjects;

import com.noticiero.udc.domain.exceptions.InvalidUserEmailException;

import java.util.Locale;

public class UserEmail {
    private final String value;

    public UserEmail(String value) {
        if (value == null || !value.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,6}$")) {
            throw new InvalidUserEmailException("El formato del correo electronico no es valido");
        }
        this.value = value.toLowerCase().trim();
    }
    public String getValue() {
        return value;
    }
}
