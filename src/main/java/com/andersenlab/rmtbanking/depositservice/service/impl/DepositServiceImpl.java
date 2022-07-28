package com.andersenlab.rmtbanking.depositservice.service.impl;

import com.andersenlab.rmtbanking.depositservice.dto.DetailedDepositDto;
import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import com.andersenlab.rmtbanking.depositservice.mapper.DepositMapper;
import com.andersenlab.rmtbanking.depositservice.repository.AgreementRepository;
import com.andersenlab.rmtbanking.depositservice.repository.DebitCardsRepository;
import com.andersenlab.rmtbanking.depositservice.service.DepositService;
import com.andersenlab.rmtbanking.depositservice.service.exeption.AgreementNotFoundException;
import com.andersenlab.rmtbanking.depositservice.service.exeption.ErrorMessage;
import com.andersenlab.rmtbanking.depositservice.service.exeption.InvalidUuidException;
import com.andersenlab.rmtbanking.depositservice.service.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private final AgreementRepository agreementRepository;
    private final DebitCardsRepository debitCardsRepository;
    private final DepositMapper depositMapper;

    @Override
    @Transactional(readOnly = true)
    public DetailedDepositDto getDetailedDeposit(String agreementId, String cardId) {
        return depositMapper.toDetailedDepositDto(findAgreementById(agreementId), findCardById(cardId));
    }

    private Agreement findAgreementById(String agreementId) {
        return agreementRepository.findById(UuidUtil.UuidFromString(agreementId)
                        .orElseThrow(() -> new InvalidUuidException(ErrorMessage.UUID_INVALID)))
                .orElseThrow(() -> new AgreementNotFoundException(ErrorMessage.AGREEMENT_NOT_FOUND));
    }

    private Card findCardById(String cardId) {
        return debitCardsRepository.findById(UuidUtil.UuidFromString(cardId)
                        .orElseThrow(() -> new InvalidUuidException(ErrorMessage.UUID_INVALID)))
                .orElseThrow(() -> new AgreementNotFoundException(ErrorMessage.CARD_NOT_FOUND));
    }
}
