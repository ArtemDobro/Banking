package com.andersenlab.rmtbanking.depositservice.service.exeption;

public class ProductIsInactiveException extends RuntimeException {
    public ProductIsInactiveException(String massage) {
        super(massage);
    }
}