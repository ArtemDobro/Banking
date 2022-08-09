package com.andersenlab.rmtbanking.depositservice.service;

import com.andersenlab.rmtbanking.depositservice.entity.Product;

import java.util.List;

public interface AvailableDepositsService {
    List<Product> getAllActiveProducts();
}