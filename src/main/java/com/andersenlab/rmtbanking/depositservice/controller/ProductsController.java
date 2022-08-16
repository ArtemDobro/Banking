package com.andersenlab.rmtbanking.depositservice.controller;

import com.andersenlab.rmtbanking.depositservice.entity.Product;
import com.andersenlab.rmtbanking.depositservice.service.impl.AvailableDepositsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/auth/deposit-products")
@RestController
@RequiredArgsConstructor
public class ProductsController {

    private final AvailableDepositsServiceImpl service;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllAvailableProducts() {
        return service.getAllActiveProducts();
    }
}