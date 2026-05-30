package com.noticiero.udc.domain.exceptions;

public class InvalidUserTokenException extends RuntimeException {
    public InvalidUserTokenException(String message) {
        super(message);
    }
}
