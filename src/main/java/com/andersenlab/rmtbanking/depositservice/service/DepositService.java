package com.andersenlab.rmtbanking.depositservice.service;

import com.andersenlab.rmtbanking.depositservice.dto.DepositDto;
import com.andersenlab.rmtbanking.depositservice.dto.DetailedDepositDto;

import java.util.List;

public interface DepositService {

    /**
     * Return DetailedDepositDto of current deposit.
     *
     * @param agreementId {@link String} agreement id
     * @param cardId      {@link String} card id
     * @return {@link DetailedDepositDto}.
     */
    DetailedDepositDto getDetailedDeposit(String agreementId, String cardId);

    /**
     * Return list of {@link DepositDto} of account with specific client id.
     *
     * @param clientId {@link String} client id
     * @return {@link List} of DepositDto
     */
    List<DepositDto> getAllDeposits(String clientId);
}
