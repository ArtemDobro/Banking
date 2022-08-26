package com.andersenlab.rmtbanking.depositservice.service;

import com.andersenlab.rmtbanking.depositservice.dto.SwitcherDto;

public interface AgreementService {
    void setAutoRenewal(SwitcherDto switcherDto);
}