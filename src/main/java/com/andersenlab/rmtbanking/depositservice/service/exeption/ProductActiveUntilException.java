package com.andersenlab.rmtbanking.depositservice.service.exeption;

public class ProductActiveUntilException extends RuntimeException{

    public ProductActiveUntilException(String massage) {
        super(massage);
    }
}