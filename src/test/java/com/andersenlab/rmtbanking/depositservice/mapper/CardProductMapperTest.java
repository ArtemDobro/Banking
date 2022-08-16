package com.andersenlab.rmtbanking.depositservice.mapper;

import com.andersenlab.rmtbanking.depositservice.dto.CardProductDto;
import com.andersenlab.rmtbanking.depositservice.entity.CardProduct;
import com.andersenlab.rmtbanking.depositservice.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Card products mapper test class")
class CardProductMapperTest {

    private final CardProductMapper cardProductMapper = new CardProductMapperImpl();

    @Test
    @DisplayName("Card product to Dto test method")
    void fromEntityToDto() {
        CardProduct cardProduct = EntityCreator.getCardProduct();
        CardProductDto cardProductDto = cardProductMapper.toDto(cardProduct);
        compareEntityWithDto(cardProduct, cardProductDto);
    }

    @Test
    @DisplayName("Card product list to list Dto's test method")
    void debitCardsToDebitCardsDto() {
        List<CardProduct> cardProductList = List.of(EntityCreator.getCardProduct());
        List<CardProductDto> cardProductDtos = cardProductMapper.cardProductsToCardProductDtos(cardProductList);
        compareProductListWithListDto(cardProductList, cardProductDtos);
    }

    private void compareEntityWithDto(CardProduct cardProduct, CardProductDto cardProductDto) {
        assertAll(
                () -> assertEquals(cardProduct.getId().toString(), cardProductDto.getId().toString()),
                () -> assertEquals(cardProduct.getCardName(), cardProductDto.getCardName()),
                () -> assertEquals(cardProduct.getPaymentSystem(), cardProductDto.getPaymentSystem()),
                () -> assertEquals(cardProduct.getCashBack(), cardProductDto.getCashBack()),
                () -> assertEquals(cardProduct.isVirtual(), cardProductDto.isVirtual()),
                () -> assertEquals(cardProduct.getPremiumStatus(), cardProductDto.getPremiumStatus()),
                () -> assertEquals(cardProduct.getServicePrice(), cardProductDto.getServicePrice()),
                () -> assertEquals(cardProduct.getProductPrice(), cardProductDto.getProductPrice()),
                () -> assertEquals(cardProduct.getCurrencyCode(), cardProductDto.getCurrencyCode()),
                () -> assertEquals(cardProduct.isActive(), cardProductDto.isActive()),
                () -> assertEquals(cardProduct.getCardDuration(), cardProductDto.getCardDuration())
        );
    }

    private void compareProductListWithListDto(List<CardProduct> cardProductList, List<CardProductDto> cardProductDtos) {
        assertEquals(cardProductList.size(), cardProductDtos.size());
        for (int s = 0; s < cardProductList.size(); s++) {
            compareEntityWithDto(cardProductList.get(s), cardProductDtos.get(s));
        }
    }
}