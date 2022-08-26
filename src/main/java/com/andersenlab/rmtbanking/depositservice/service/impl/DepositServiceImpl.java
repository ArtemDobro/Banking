package com.andersenlab.rmtbanking.depositservice.service.impl;

import com.andersenlab.rmtbanking.depositservice.dto.DepositDto;
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
import com.andersenlab.rmtbanking.depositservice.service.exeption.InvalidUuidException;
import com.andersenlab.rmtbanking.depositservice.service.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepositServiceImpl implements DepositService {

    private final AgreementRepository agreementRepository;
    private final DebitCardsRepository debitCardsRepository;
    private final AccountRepository accountRepository;
    private final DepositMapper depositMapper;

    @Override
    @Transactional(readOnly = true)
    public DetailedDepositDto getDetailedDeposit(String agreementId, String cardId) {
        log.info("getDetailedDeposit about card with id {}", cardId);
        return depositMapper.toDetailedDepositDto(findAgreementById(agreementId), findCardById(cardId));
    }

    private Agreement findAgreementById(String agreementId) {
        log.info("findAgreementById with agreementId {}", agreementId);
        return agreementRepository.findById(UuidUtil.UuidFromString(agreementId)
                        .orElseThrow(() -> new InvalidUuidException(ErrorMessage.UUID_INVALID)))
                .orElseThrow(() -> new AgreementNotFoundException(ErrorMessage.AGREEMENT_NOT_FOUND));
    }

    private Card findCardById(String cardId) {
        log.info("findCardById with cardId {}", cardId);
        return debitCardsRepository.findById(UuidUtil.UuidFromString(cardId)
                        .orElseThrow(() -> new InvalidUuidException(ErrorMessage.UUID_INVALID)))
                .orElseThrow(() -> new AgreementNotFoundException(ErrorMessage.CARD_NOT_FOUND));
    }

    public List<DepositDto> getAllDeposits(String clientId) {
        return depositMapper.agreementsToDepositDtoList(agreementRepository.getAgreementsByClientIdAndAccountStatus(UUID.fromString(clientId), true));
    }
}