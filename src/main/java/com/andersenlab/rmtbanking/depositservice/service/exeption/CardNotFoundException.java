package com.andersenlab.rmtbanking.depositservice.service.exeption;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String message) {
        super(message);
    }
}