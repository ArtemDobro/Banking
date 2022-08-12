package com.andersenlab.rmtbanking.depositservice.service.impl;

import com.andersenlab.rmtbanking.depositservice.dto.SwitcherDto;
import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.repository.AgreementRepository;
import com.andersenlab.rmtbanking.depositservice.service.AgreementService;
import com.andersenlab.rmtbanking.depositservice.service.exeption.AgreementNotFoundException;
import com.andersenlab.rmtbanking.depositservice.service.exeption.ErrorMessage;
import com.andersenlab.rmtbanking.depositservice.service.strategy.SwitcherStrategy;
import com.andersenlab.rmtbanking.depositservice.service.strategy.SwitcherStrategyPicker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository repository;
    private final SwitcherStrategyPicker switcherStrategyPicker;

    @Override
    @Transactional
    public void setAutoRenewal(SwitcherDto switcherDto) {
        log.info("set switcherDto with id {}", switcherDto.getAccountId());
        Agreement agreement = getAgreement(switcherDto);

        Optional<Boolean> autoRenewal = getIsAutoRenewal(switcherDto, agreement);
        autoRenewal.ifPresent(v -> {
            agreement.setAutoRenewal(v);
            repository.save(agreement);
        });
    }

    private Optional<Boolean> getIsAutoRenewal(SwitcherDto switcherDto, Agreement agreement) {
        log.info("getIsAutoRenewal for switcherDto with id {}", switcherDto.getAccountId());
        SwitcherStrategy switcherStrategy = switcherStrategyPicker.getSwitcherStrategy(switcherDto.getIsTurnOn());
        return switcherStrategy.getAutoRenewal(
                switcherDto.getIsTurnOn(),
                agreement.isAutoRenewal(),
                agreement
        );
    }

    private Agreement getAgreement(SwitcherDto switcherDto) {
        log.info("getAgreement for switcherDto with id {}", switcherDto.getAccountId());
        return repository.findByAccountId(UUID.fromString(switcherDto.getAccountId()))
                .orElseThrow(() -> new AgreementNotFoundException(ErrorMessage.AGREEMENT_STATUS));
    }
}
