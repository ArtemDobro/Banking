package com.andersenlab.rmtbanking.depositservice.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class DepositDto {

    String agreementId;

    LocalDateTime startDate;

    LocalDateTime endDate;

    BigDecimal currentBalance;

    String productName;

    String currencyCode;

    String cardId;
}
