package com.andersenlab.rmtbanking.depositservice.controller;

import com.andersenlab.rmtbanking.depositservice.entity.Product;
import com.andersenlab.rmtbanking.depositservice.service.impl.AvailableDepositsServiceImpl;
import com.andersenlab.rmtbanking.depositservice.validation.annotation.Uuid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Validated
@RequestMapping("/auth/deposit-products")
@RestController
@RequiredArgsConstructor
public class ProductsController {

    private final AvailableDepositsServiceImpl service;
    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllAvailableProducts(@Uuid @PathVariable("clientId") String clientId){
        return service.getAllActiveProducts();
    }
}