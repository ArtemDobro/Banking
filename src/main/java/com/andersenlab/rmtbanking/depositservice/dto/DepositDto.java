package com.andersenlab.rmtbanking.depositservice.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Value
public class DepositDto {

    String agreementId;

    Timestamp startDate;

    Timestamp endDate;

    BigDecimal currentBalance;

    String productName;

    String currencyCode;

    String cardId;
}
