package com.andersenlab.rmtbanking.depositservice.service.impl;

import com.andersenlab.rmtbanking.depositservice.dto.DetailedDepositDto;
import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import com.andersenlab.rmtbanking.depositservice.mapper.DepositMapper;
import com.andersenlab.rmtbanking.depositservice.repository.AccountRepository;
import com.andersenlab.rmtbanking.depositservice.repository.AgreementRepository;
import com.andersenlab.rmtbanking.depositservice.repository.DebitCardsRepository;
import com.andersenlab.rmtbanking.depositservice.service.DepositService;
import com.andersenlab.rmtbanking.depositservice.service.exeption.AgreementNotFoundException;
import com.andersenlab.rmtbanking.depositservice.service.exeption.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private final AccountRepository accountRepository;
    private final AgreementRepository agreementRepository;
    private final DebitCardsRepository debitCardsRepository;
    private final DepositMapper depositMapper;

    @Override
    @Transactional(readOnly = true)
    public DetailedDepositDto getDetailedDeposit(String agreementId, String cardId) {
        Agreement agreement = agreementRepository.findById(getUuid(agreementId))
                .orElseThrow(() -> new AgreementNotFoundException(ErrorMessage.AGREEMENT_NOT_FOUND));
        Card card = debitCardsRepository.findById(getUuid(cardId))
                .orElseThrow(() -> new AgreementNotFoundException(ErrorMessage.CARD_NOT_FOUND));
        return depositMapper.toDetailedDepositDto(agreement, card);
    }

    public UUID getUuid(String id) {
        return UUID.fromString(id);
    }
}
