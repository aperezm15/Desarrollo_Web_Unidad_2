package com.noticiero.udc.domain.valueobjects;

import java.util.regex.Pattern;

public class UserEmail {
    private final String value;

    // Expresión regular estándar y segura para correos electrónicos
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    public UserEmail(String value) {

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico no puede estar vacío");
        }

        String cleanedValue = value.trim();

        if (!EMAIL_PATTERN.matcher(cleanedValue).matches()) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido");
        }

        this.value = cleanedValue;
    }

    public String getValue() {
        return value;
    }
}