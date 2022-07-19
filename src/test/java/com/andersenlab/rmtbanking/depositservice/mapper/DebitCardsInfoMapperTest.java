package com.andersenlab.rmtbanking.depositservice.mapper;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsInfoDto;
import com.andersenlab.rmtbanking.depositservice.entity.Account;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import com.andersenlab.rmtbanking.depositservice.entity.enums.CardStatus;
import com.andersenlab.rmtbanking.depositservice.util.DtoCreator;
import com.andersenlab.rmtbanking.depositservice.util.EntityCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

@DisplayName("Test class for DebitCardsInfoMapper")
@ExtendWith(MockitoExtension.class)
public class DebitCardsInfoMapperTest {

    @Spy
    private DebitCardsMapper debitCardsMapper = new DebitCardsMapperImpl();

    @Test
    @DisplayName("Test to get DtoInfo")
    public void debitCardToCardInfoDto() {
        Card card = EntityCreator.getCard();
        DebitCardsInfoDto expectDto = DtoCreator.getDebitCardsInfoDto();
        Assertions.assertEquals(expectDto, debitCardsMapper.debitCardsInfoToDto(card));
    }
}