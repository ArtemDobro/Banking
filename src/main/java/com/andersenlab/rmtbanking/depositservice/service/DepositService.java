package com.andersenlab.rmtbanking.depositservice.service;

import com.andersenlab.rmtbanking.depositservice.dto.DetailedDepositDto;

public interface DepositService {

    /**
     * Return @{@link DetailedDepositDto}.
     *
     * @param agreementId @{@link String} agreement id
     * @param cardId      @{@link String} card id
     * @return @{@link DetailedDepositDto}.
     */
    DetailedDepositDto getDetailedDeposit(String agreementId, String cardId);
}
