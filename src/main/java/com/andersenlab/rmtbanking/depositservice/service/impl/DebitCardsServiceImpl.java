package com.andersenlab.rmtbanking.depositservice.service.impl;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsDto;
import com.andersenlab.rmtbanking.depositservice.mapper.DebitCardsMapper;
import com.andersenlab.rmtbanking.depositservice.repository.AccountRepository;
import com.andersenlab.rmtbanking.depositservice.repository.DebitCardsRepository;
import com.andersenlab.rmtbanking.depositservice.service.DebitCardService;
import com.andersenlab.rmtbanking.depositservice.service.exeption.ClientNotFoundException;
import com.andersenlab.rmtbanking.depositservice.service.exeption.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DebitCardsServiceImpl implements DebitCardService {

    private final DebitCardsRepository debitCardsRepository;
    private final AccountRepository accountRepository;
    private final DebitCardsMapper debitCardsMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DebitCardsDto> getAllActiveDebitCards(String clientId) {
        UUID uuid = UUID.fromString(clientId);
        accountRepository.findByClientId(uuid)
                .orElseThrow(() -> new ClientNotFoundException(ErrorMessage.CLIENT_STATUS));
        return debitCardsMapper.debitCardsToDebitCardsDto(debitCardsRepository.getCardsByAccountClientIdAndAccountActive
                (uuid, true));
    }
}