package com.andersenlab.rmtbanking.depositservice.service.strategy;

import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.repository.AgreementsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.andersenlab.rmtbanking.depositservice.util.EntityCreator.getTestAgreement;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for TurnOnSwitcherStrategy")
class TurnOnSwitcherStrategyTest {

    @Mock
    private AgreementsRepository agreementsRepository;
    @InjectMocks
    private TurnOnSwitcherStrategy turnOnSwitcherStrategy;

    @Test
    void turnOnSwitcherFirstCondition(){
        Agreement agreement = getTestAgreement();

        agreement.getProduct().setActive(false);

        turnOnSwitcherStrategy.getAutoRenewal(true, false, agreement);

        assertFalse(agreement.isAutoRenewal());
    }
    @Test
    void turnOnSwitcherSecondCondition(){
        Agreement agreement = getTestAgreement();

        agreement.setEndDate(LocalDateTime.now());

        turnOnSwitcherStrategy.getAutoRenewal(true, true,agreement);

        assertNotEquals(agreement.getEndDate(), LocalDate.now());
    }
    @Test
    void turnOnSwitcherThirdCondition(){
        Agreement agreement = getTestAgreement();

        turnOnSwitcherStrategy.getAutoRenewal(true, false, agreement);

        assertFalse(agreement.isAutoRenewal());
    }
}