package com.Email.registration.Emailregistration.exception;

public class EmailException extends RuntimeException {
    private String message;

    public EmailException(String message) {
        super(message);

    }
}
