package com.andersenlab.rmtbanking.depositservice.mapper;

import com.andersenlab.rmtbanking.depositservice.dto.DetailedDepositDto;
import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import com.andersenlab.rmtbanking.depositservice.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Deposit mapper test class")
class DepositMapperTest {

    private final DepositMapper depositMapper = new DepositMapperImpl();

    @Test
    @DisplayName("Agreement to Dto test method")
    void fromEntityToDetailedDepositDto() {
        Agreement agreement = EntityCreator.getTestAgreement();
        Card card = EntityCreator.getTestCard();
        DetailedDepositDto detailedDepositDto = depositMapper.toDetailedDepositDto(agreement, card);
        compareEntityWithDetailedDepositDto(agreement, card, detailedDepositDto);
    }

    private void compareEntityWithDetailedDepositDto(Agreement agreement, Card card, DetailedDepositDto depositDto) {
        assertAll(() -> assertEquals(card.getCardNumber(), depositDto.getCardNumber()),
                () -> assertEquals(agreement.getStartDate(), depositDto.getStartDate()),
                () -> assertEquals(agreement.getEndDate(), depositDto.getEndDate()),
                () -> assertEquals(agreement.getInterestRate(), depositDto.getInterestRate()),
                () -> assertEquals(agreement.getCurrentBalance(), depositDto.getCurrentBalance()),
                () -> assertEquals(agreement.isAutoRenewal(), depositDto.isAutoRenewal()),
                () -> assertEquals(agreement.getProduct().getName(), depositDto.getName()),
                () -> assertEquals(agreement.getProduct().getCurrencyCode(), depositDto.getCurrencyCode()),
                () -> assertEquals(agreement.getProduct().getSchemaName(), depositDto.getSchemaName()),
                () -> assertEquals(agreement.getProduct().isCapitalization(), depositDto.isCapitalization()),
                () -> assertEquals(agreement.getProduct().isRevocable(), depositDto.isRevocable()));
    }
}
