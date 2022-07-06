package com.andersenlab.rmtbanking.depositservice.service.exeption;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}