package com.andersenlab.rmtbanking.depositservice.controller;

import com.andersenlab.rmtbanking.depositservice.dto.CardProductDto;
import com.andersenlab.rmtbanking.depositservice.service.CardProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("auth/cards-products")
@RestController
@RequiredArgsConstructor
public class CardProductController {

    private final CardProductService cardProductService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CardProductDto> getAllCardProducts() {
        return cardProductService.findAllActiveProducts();
    }
}
