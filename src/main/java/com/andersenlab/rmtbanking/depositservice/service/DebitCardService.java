package com.andersenlab.rmtbanking.depositservice.service;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsDto;
import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsInfoDto;

import java.util.List;

public interface DebitCardService {
    List<DebitCardsDto> getAllActiveDebitCards(String clientId);

    DebitCardsInfoDto getOneDebitCardInfo(String cardId);
}