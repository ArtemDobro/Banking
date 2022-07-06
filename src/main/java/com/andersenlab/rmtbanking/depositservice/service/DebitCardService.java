package com.andersenlab.rmtbanking.depositservice.service;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsDto;

import java.util.List;

public interface DebitCardService {
    List<DebitCardsDto> getAllActiveDebitCards(String clientId);
}