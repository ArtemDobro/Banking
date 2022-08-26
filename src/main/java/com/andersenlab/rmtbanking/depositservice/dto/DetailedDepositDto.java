package com.andersenlab.rmtbanking.depositservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class DetailedDepositDto {

    String cardNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime endDate;

    BigDecimal interestRate;

    BigDecimal currentBalance;

    boolean autoRenewal;

    String name;

    String currencyCode;

    String schemaName;

    boolean isCapitalization;

    boolean isRevocable;
}
