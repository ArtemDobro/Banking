package com.andersenlab.rmtbanking.depositservice.service;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsDto;
import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsInfoDto;

import java.util.List;

public interface DebitCardService {
    /**
     * getting uuid from string -> finding account by it's uuid or throw exception
     * @param clientId
     * @return if uuid is correct and account is active we return List of all active debit cards
     */
    List<DebitCardsDto> getAllActiveDebitCards(String clientId);

    /**
     * getting uuid from string -> finding account by it's uuid
     * @param cardId
     * @return if uuid is correct and account is active we return one card
     */
    DebitCardsInfoDto getOneDebitCardInfo(String cardId);
}