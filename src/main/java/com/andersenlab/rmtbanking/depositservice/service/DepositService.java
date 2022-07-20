package com.andersenlab.rmtbanking.depositservice.service;

import com.andersenlab.rmtbanking.depositservice.dto.DetailedDepositDto;

public interface DepositService {
    DetailedDepositDto getDetailedDeposit(String agreementId, String cardId);
}
