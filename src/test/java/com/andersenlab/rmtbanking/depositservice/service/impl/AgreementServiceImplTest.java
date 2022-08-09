package com.andersenlab.rmtbanking.depositservice.service.impl;

import com.andersenlab.rmtbanking.depositservice.dto.SwitcherDto;
import com.andersenlab.rmtbanking.depositservice.entity.Account;
import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.entity.Product;
import com.andersenlab.rmtbanking.depositservice.repository.AgreementRepository;
import com.andersenlab.rmtbanking.depositservice.service.strategy.SwitcherStrategyPicker;
import com.andersenlab.rmtbanking.depositservice.service.strategy.TurnOffSwitcherStrategy;
import com.andersenlab.rmtbanking.depositservice.service.strategy.TurnOnSwitcherStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.andersenlab.rmtbanking.depositservice.util.DtoCreator.getSwitcherDto;
import static com.andersenlab.rmtbanking.depositservice.util.EntityCreator.getTestAccount;
import static com.andersenlab.rmtbanking.depositservice.util.EntityCreator.getTestAgreement;
import static com.andersenlab.rmtbanking.depositservice.util.EntityCreator.getTestProduct;
import static java.util.Optional.of;
import static java.util.UUID.fromString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for AgreementServiceImpl")
class AgreementServiceImplTest {
    private static final LocalDate endDate = LocalDate.now();

    @Mock
    private AgreementRepository agreementsRepository;
    @Mock
    private SwitcherStrategyPicker switcherStrategyPicker;
    @Mock
    private TurnOnSwitcherStrategy turnOnSwitcherStrategy;
    @Mock
    private TurnOffSwitcherStrategy turnOffSwitcherStrategy;
    @InjectMocks
    private AgreementServiceImpl agreementService;

    Agreement agreement;
    SwitcherDto dto;
    Account account;
    Product product;

    @BeforeEach
    void init() {
        agreement = getTestAgreement();
        dto = getSwitcherDto();
        account = getTestAccount();
        product = getTestProduct();

        agreement.setProduct(product);
        agreement.setAccount(account);

        when(agreementsRepository.findByAccountId(fromString(dto.getAccountId())))
                .thenReturn(of(agreement));
    }

    @AfterEach
    void clearMocks() {
        Mockito.clearInvocations(agreementsRepository);
    }

    @Test
    void turnOff() {
        when(switcherStrategyPicker.getSwitcherStrategy(dto.getIsTurnOn()))
                .thenReturn(turnOffSwitcherStrategy);
        when(turnOffSwitcherStrategy.getAutoRenewal(dto.getIsTurnOn(), agreement.isAutoRenewal(), agreement))
                .thenReturn(of(false));

        agreementService.setAutoRenewal(dto);

        verify(agreementsRepository, times(1)).save(any());
    }


    @Test
    void turnOn() {
        when(switcherStrategyPicker.getSwitcherStrategy(dto.getIsTurnOn()))
                .thenReturn(turnOnSwitcherStrategy);
        when(turnOnSwitcherStrategy.getAutoRenewal(dto.getIsTurnOn(), agreement.isAutoRenewal(), agreement))
                .thenReturn(of(true));

        agreementService.setAutoRenewal(dto);

        verify(agreementsRepository, times(1)).save(any());
    }

}