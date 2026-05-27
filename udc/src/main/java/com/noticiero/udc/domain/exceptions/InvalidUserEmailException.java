package com.noticiero.udc.domain.exceptions;



public class InvalidUserEmailException extends RuntimeException {
    public InvalidUserEmailException(String message) {
        super(message);
    }

}
