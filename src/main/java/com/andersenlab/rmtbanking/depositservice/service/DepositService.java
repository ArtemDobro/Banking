package com.andersenlab.rmtbanking.depositservice.service;

import com.andersenlab.rmtbanking.depositservice.dto.DepositDto;
import com.andersenlab.rmtbanking.depositservice.dto.DetailedDepositDto;

import java.util.List;

public interface DepositService {
    List<DepositDto> getAllDeposits(String clientId);
}
