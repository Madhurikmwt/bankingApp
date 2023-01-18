package com.swedbank.microservices.accountservice.exception;

public class ParameterValidationException extends RuntimeException {

    public ParameterValidationException() {
        super();
    }

    public ParameterValidationException(String message) {
        super(message);
    }

}
