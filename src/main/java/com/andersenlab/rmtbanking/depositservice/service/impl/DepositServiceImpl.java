package com.andersenlab.rmtbanking.depositservice.service.impl;

import com.andersenlab.rmtbanking.depositservice.dto.DepositDto;
import com.andersenlab.rmtbanking.depositservice.mapper.DepositMapper;
import com.andersenlab.rmtbanking.depositservice.repository.AccountRepository;
import com.andersenlab.rmtbanking.depositservice.repository.AgreementRepository;
import com.andersenlab.rmtbanking.depositservice.repository.DebitCardsRepository;
import com.andersenlab.rmtbanking.depositservice.service.DepositService;
import com.andersenlab.rmtbanking.depositservice.service.exeption.ClientNotFoundException;
import com.andersenlab.rmtbanking.depositservice.service.exeption.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public List<DepositDto> getAllDeposits(String clientId) {
        UUID uuid = UUID.fromString(clientId);

        accountRepository.findByClientId(uuid)
                .orElseThrow(() -> new ClientNotFoundException(ErrorMessage.CLIENT_STATUS));

        return depositMapper.agreementsToDepositDtoList(agreementRepository.getAgreementsByAccountClientIdAndAccountActive(uuid, true));
    }
}
