package com.andersenlab.rmtbanking.depositservice.dto;

import com.andersenlab.rmtbanking.depositservice.entity.enums.PaymentSystem;
import com.andersenlab.rmtbanking.depositservice.entity.enums.PremiumStatus;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class CardProductDto {

    Long id;

    String cardName;

    PremiumStatus premiumStatus;

    PaymentSystem paymentSystem;

    BigDecimal cashBack;

    String coBrand;

    boolean virtual;

    String currencyCode;

    BigDecimal productPrice;

    BigDecimal servicePrice;

    boolean active;

    Integer cardDuration;
}
