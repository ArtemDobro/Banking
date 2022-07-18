package com.andersenlab.rmtbanking.depositservice.dto;

import lombok.Value;

@Value
public class DebitCardsInfoDto {

    String accountNumber;
    String salaryProject;
    String expirationDate;
    String currentBalance;
    String currencyCode;
    String status;
}