package com.andersenlab.rmtbanking.depositservice.service.impl;

import com.andersenlab.rmtbanking.depositservice.dto.CardProductDto;
import com.andersenlab.rmtbanking.depositservice.mapper.CardProductMapper;
import com.andersenlab.rmtbanking.depositservice.repository.CardProductRepository;
import com.andersenlab.rmtbanking.depositservice.service.CardProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardProductServiceImpl implements CardProductService {

    private final CardProductRepository cardProductRepository;
    private final CardProductMapper cardProductMapper;

    @Override
    public List<CardProductDto> findAllActiveProducts() {
        return cardProductMapper.cardProductsToCardProductDtos(cardProductRepository.findAllCardProducts(true));
    }
}
