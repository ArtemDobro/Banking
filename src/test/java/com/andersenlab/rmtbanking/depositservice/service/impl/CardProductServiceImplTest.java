package com.andersenlab.rmtbanking.depositservice.service.impl;

import com.andersenlab.rmtbanking.depositservice.dto.CardProductDto;
import com.andersenlab.rmtbanking.depositservice.entity.CardProduct;
import com.andersenlab.rmtbanking.depositservice.mapper.CardProductMapper;
import com.andersenlab.rmtbanking.depositservice.repository.CardProductRepository;
import com.andersenlab.rmtbanking.depositservice.util.DtoCreator;
import com.andersenlab.rmtbanking.depositservice.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for CardProductServiceImpl")
class CardProductServiceImplTest {

    @Mock
    private CardProductMapper cardProductMapper;
    @Mock
    private CardProductRepository cardProductRepository;
    @InjectMocks
    private CardProductServiceImpl cardProductService;

    @Test
    @DisplayName("Get all active card products test method")
    public void getAllActiveDebitCards() {
        List<CardProduct> cardProducts = List.of(EntityCreator.getCardProduct());
        List<CardProductDto> cardProductDtos = List.of(DtoCreator.getCardProductDto());

        when(cardProductRepository.findAllCardProducts(true)).thenReturn(cardProducts);
        when(cardProductMapper.cardProductsToCardProductDtos(cardProducts)).thenReturn(cardProductDtos);

        List<CardProductDto> actualCardProductListDto = cardProductService.findAllActiveProducts();
        assertEquals(actualCardProductListDto, cardProductDtos);
    }

}