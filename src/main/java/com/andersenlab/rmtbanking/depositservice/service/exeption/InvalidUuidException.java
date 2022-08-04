package com.andersenlab.rmtbanking.depositservice.service.exeption;

public class InvalidUuidException extends RuntimeException {
    public InvalidUuidException(String message) {
        super(message);
    }
}
