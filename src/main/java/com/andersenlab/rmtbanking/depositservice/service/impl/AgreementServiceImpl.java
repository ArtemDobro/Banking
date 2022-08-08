package com.andersenlab.rmtbanking.depositservice.service.impl;

import com.andersenlab.rmtbanking.depositservice.dto.SwitcherDto;
import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.repository.AgreementsRepository;
import com.andersenlab.rmtbanking.depositservice.service.AgreementService;
import com.andersenlab.rmtbanking.depositservice.service.exeption.AgreementNotFoundException;
import com.andersenlab.rmtbanking.depositservice.service.exeption.ErrorMessage;
import com.andersenlab.rmtbanking.depositservice.service.strategy.SwitcherStrategy;
import com.andersenlab.rmtbanking.depositservice.service.strategy.SwitcherStrategyPicker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementsRepository repository;
    private final SwitcherStrategyPicker switcherStrategyPicker;

    @Override
    @Transactional
    public void setAutoRenewal(SwitcherDto switcherDto) {
        Agreement agreement = getAgreement(switcherDto);

        Optional<Boolean> autoRenewal = getIsAutoRenewal(switcherDto, agreement);
        autoRenewal.ifPresent(v -> {
            agreement.setAutoRenewal(v);
            repository.save(agreement);
        });
    }

    private Optional<Boolean> getIsAutoRenewal(SwitcherDto switcherDto, Agreement agreement) {
        SwitcherStrategy switcherStrategy = switcherStrategyPicker.getSwitcherStrategy(switcherDto.getIsTurnOn());
        return switcherStrategy.getAutoRenewal(
                switcherDto.getIsTurnOn(),
                agreement.isAutoRenewal(),
                agreement
        );
    }

    private Agreement getAgreement(SwitcherDto switcherDto) {
        return repository.findByAccountId(UUID.fromString(switcherDto.getAccountId()))
                .orElseThrow(() -> new AgreementNotFoundException(ErrorMessage.AGREEMENT_STATUS));
    }
}
