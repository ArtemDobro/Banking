package com.andersenlab.rmtbanking.depositservice.service;

import com.andersenlab.rmtbanking.depositservice.dto.CardProductDto;

import java.util.List;

public interface CardProductService {

    /**
     * Return @{@link List} of active CardProductDto.
     *
     * @return @{@link List} of CardProductDto.
     */
    List<CardProductDto> findAllActiveProducts();
}
