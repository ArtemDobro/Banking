package com.andersenlab.rmtbanking.depositservice.dto;

import com.andersenlab.rmtbanking.depositservice.entity.enums.CardStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class DebitCardsDto {

    String id;

    String cardNumber;

    String accountNumber;

    BigDecimal transactionLimit;

    CardStatus status;

    LocalDate expirationDate;

    String holderName;

    boolean virtualCard;

    String digitalWallet;

    boolean defaults;
}