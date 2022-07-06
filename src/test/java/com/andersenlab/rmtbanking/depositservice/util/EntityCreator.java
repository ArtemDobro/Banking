package com.andersenlab.rmtbanking.depositservice.util;

import com.andersenlab.rmtbanking.depositservice.entity.Account;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import com.andersenlab.rmtbanking.depositservice.entity.CardProduct;
import com.andersenlab.rmtbanking.depositservice.entity.enums.CardStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

public class EntityCreator {

    public static Card getCard() {
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

    public static Account getTestAccount() {
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setAccountNumber("account_number");
        account.setClientId(UUID.randomUUID());
        account.setCurrencyCode("USD");
        account.setCurrentBalance(BigDecimal.valueOf(9999999));
        account.setOpenDate(LocalDate.of(2022, 5, 3));
        account.setCloseDate(LocalDate.of(2025, 6,6));
        account.setActive(true);
        account.setSalaryProject("YES");
        account.setBlockedSum(BigDecimal.valueOf(11111));
        return account;
    }
}