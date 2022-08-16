package com.andersenlab.rmtbanking.depositservice.mapper;

import com.andersenlab.rmtbanking.depositservice.dto.DepositDto;
import com.andersenlab.rmtbanking.depositservice.dto.DetailedDepositDto;
import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import com.andersenlab.rmtbanking.depositservice.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        compareCardWithDetailedDepositDto(card, detailedDepositDto);
        compareAgreementWithDetailedDepositDto(agreement, detailedDepositDto);
    }

    private void compareCardWithDetailedDepositDto(Card card, DetailedDepositDto depositDto) {
        assertEquals(card.getCardNumber(), depositDto.getCardNumber());
    }

    private void compareAgreementWithDetailedDepositDto(Agreement agreement, DetailedDepositDto depositDto) {
        assertAll(() -> assertEquals(agreement.getStartDate(), depositDto.getStartDate()),
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

    @Test
    @DisplayName("Agreement to Dto test method")
    void fromEntityToDepositDto() {
        Agreement agreement = EntityCreator.getTestAgreement();
        DepositDto depositDto = depositMapper.toDepositDto(agreement);
        compareEntityWithDto(agreement, depositDto);
    }

    @Test
    @DisplayName("Agreement list to list Dto's test method")
    void agreementsToDepositDtoList() {
        List<Agreement> agreementList = List.of(EntityCreator.getTestAgreement());
        List<DepositDto> depositDtoList = depositMapper.agreementsToDepositDtoList(agreementList);
        compareEntityListWithDtoList(agreementList, depositDtoList);
    }

    private void compareEntityWithDto(Agreement agreement, DepositDto depositDto) {
        assertAll(() -> assertEquals(agreement.getId()
                        .toString(), depositDto.getAgreementId()),
                () -> assertEquals(agreement.getStartDate(), depositDto.getStartDate()),
                () -> assertEquals(agreement.getEndDate(), depositDto.getEndDate()),
                () -> assertEquals(agreement.getCurrentBalance(), depositDto.getCurrentBalance()),
                () -> assertEquals(agreement.getProduct().getName(), depositDto.getProductName()),
                () -> assertEquals(agreement.getProduct().getCurrencyCode(), depositDto.getCurrencyCode()));
    }

    private void compareEntityListWithDtoList(List<Agreement> agreementList, List<DepositDto> agreementDtoList) {
        assertEquals(agreementList.size(), agreementDtoList.size());
        for (int i = 0; i < agreementList.size(); i++) {
            compareEntityWithDto(agreementList.get(i), agreementDtoList.get(i));
        }
    }
}