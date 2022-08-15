package com.andersenlab.rmtbanking.depositservice.util;

import com.andersenlab.rmtbanking.depositservice.entity.Account;
import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import com.andersenlab.rmtbanking.depositservice.entity.Product;
import com.andersenlab.rmtbanking.depositservice.entity.enums.CardStatus;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.UUID;

@UtilityClass
public class EntityCreator {

    public static Card getTestCard() {
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
        account.setCloseDate(LocalDate.of(2025, 6, 6));
        account.setActive(true);
        account.setSalaryProject("YES");
        account.setBlockedSum(BigDecimal.valueOf(11111));
        account.setCards(Collections.emptyList());
        return account;
    }

    public static Agreement getTestAgreement() {
        Agreement agreement = new Agreement();
        agreement.setId(UUID.randomUUID());
        agreement.setAgreementNumber("number from ABS 1");
        agreement.setInterestRate(BigDecimal.valueOf(4.5));
        agreement.setStartDate(LocalDateTime.of(2020, 07, 20, 12, 12));
        agreement.setEndDate(LocalDateTime.of(2024, 12, 8,12,12));
        agreement.setInitialAmount(BigDecimal.valueOf(45000));
        agreement.setCurrentBalance(BigDecimal.valueOf(10000));
        agreement.setActive(true);
        agreement.setAutoRenewal(false);
        agreement.setProduct(EntityCreator.getTestProduct());
        agreement.setAccount(EntityCreator.getTestAccount());
        return agreement;

    }

    public static Product getTestProduct() {
        Product product = new Product();
        product.setId(233333333L);
        product.setName("name for deposit product 1");
        product.setSchemaName("RECCURING");
        product.setInterestRateEarly(BigDecimal.valueOf(20));
        product.setCapitalization(true);
        product.setAmountMin(BigDecimal.valueOf(1000));
        product.setAmountMax(BigDecimal.valueOf(100000));
        product.setCurrencyCode("USD");
        product.setActive(true);
        product.setRevocable(true);
        product.setMinDurationMonths(6);
        product.setMinInterestRate(BigDecimal.valueOf(3.5));
        product.setMaxInterestRate(BigDecimal.valueOf(6.6));
        product.setActiveSince(LocalDate.of(2022, 7, 14));
        product.setActiveUntil(LocalDate.of(2026, 7, 14));
        return product;
    }
}