package com.andersenlab.rmtbanking.depositservice.service.strategy;

import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.repository.AgreementRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.andersenlab.rmtbanking.depositservice.util.EntityCreator.getTestAgreement;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for TurnOffSwitcherStrategy")
class TurnOffSwitcherStrategyTest {

    @Mock
    private AgreementRepository agreementsRepository;
    @InjectMocks
    private TurnOffSwitcherStrategy turnOffSwitcherStrategy;

    @Test
    void turnOffSwitcherTest() {
        Agreement agreement = getTestAgreement();

        turnOffSwitcherStrategy.getAutoRenewal(true, false, agreement);

        assertFalse(agreement.isAutoRenewal());
    }
}