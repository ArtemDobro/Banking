package com.andersenlab.rmtbanking.depositservice.service;

import com.andersenlab.rmtbanking.depositservice.dto.DepositDto;

import java.util.List;


public interface DepositService {
    /**
     * Return list of @{@link DepositDto} of account with specific client id.
     *
     * @param clientId @{@link String} client id
     * @return @{@link List} of @{@link DepositDto}
     */
    List<DepositDto> getAllDeposits(String clientId);
}
