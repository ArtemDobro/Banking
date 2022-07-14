package com.andersenlab.rmtbanking.depositservice.mapper;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsInfoDto;
import com.andersenlab.rmtbanking.depositservice.entity.Account;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import com.andersenlab.rmtbanking.depositservice.entity.enums.CardStatus;
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
    private DebitCardsMapper debitCardsInfoMapper = new DebitCardsMapperImpl();

    @Test
    @DisplayName("Test to get DtoInfo")
    public void debitCardToCardInfoDto() {
        Card card = getCard();
        DebitCardsInfoDto expectDto = new DebitCardsInfoDto("account_number",
                "salary_project",
                "2222-01-01",
                "9999999",
                "USD",
                "EXPIRED"
        );
        Assertions.assertEquals(expectDto, debitCardsInfoMapper.debitCardsInfoToDto(card));
    }


    Card getCard() {
        Card card = new Card();
        card.setAccount(getTestAccount());
        card.setId(UUID.randomUUID());
        card.setCardNumber("THE");
        card.setTransactionLimit(new BigDecimal(9999999));
        card.setStatus(CardStatus.EXPIRED);
        card.setExpirationDate(LocalDate.of(2222, Month.JANUARY, 1));
        card.setHolderName("Monica");
        card.setVirtualCard(true);
        card.setDigitalWallet("444");
        card.setDefaults(true);
        return card;
    }

    Account getTestAccount() {
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setAccountNumber("account_number");
        account.setClientId(UUID.randomUUID());
        account.setCurrencyCode("USD");
        account.setCurrentBalance(BigDecimal.valueOf(9999999));
        account.setOpenDate(LocalDate.of(2022, 5, 3));
        account.setCloseDate(LocalDate.of(2025, 6, 6));
        account.setActive(true);
        account.setSalaryProject("salary_project");
        account.setBlockedSum(BigDecimal.valueOf(11111));
        return account;
    }
}