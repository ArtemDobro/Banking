package com.andersenlab.rmtbanking.depositservice.service.exeption;

public class CardProductNotFound extends RuntimeException {
    public CardProductNotFound(String message) {
        super(message);
    }
}
