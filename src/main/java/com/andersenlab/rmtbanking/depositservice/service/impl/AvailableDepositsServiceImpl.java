package com.andersenlab.rmtbanking.depositservice.service.impl;

import com.andersenlab.rmtbanking.depositservice.entity.Product;
import com.andersenlab.rmtbanking.depositservice.repository.ProductRepository;
import com.andersenlab.rmtbanking.depositservice.service.AvailableDepositsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailableDepositsServiceImpl implements AvailableDepositsService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllActiveProducts() {
        return productRepository.getProductsByActive(true);
    }
}