package com.andersenlab.rmtbanking.depositservice.service.strategy;

import com.andersenlab.rmtbanking.depositservice.entity.Agreement;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

public abstract class SwitcherStrategy {
    public abstract Optional<Boolean> getAutoRenewal(Boolean isTurnOn, Boolean isAutoRenewal, Agreement agreement);

    protected Map<Boolean, BiFunction<Boolean, Boolean, Boolean>> getFunctionMap() {
        BiFunction<Boolean, Boolean, Boolean> trueFunc = this::getAutoRenewalValueIfSwitchedToTrue;
        BiFunction<Boolean, Boolean, Boolean> falseFunc = this::getAutoRenewalValueIfSwitchedToFalse;

        Map<Boolean, BiFunction<Boolean, Boolean, Boolean>> funcMap = Map.of(true, trueFunc, false,falseFunc);
        return funcMap;
    }

    protected Boolean getAutoRenewalValueIfSwitchedToFalse(Boolean isAutoRenewal, Boolean isTurnOn) {
        return Boolean.TRUE.equals(isAutoRenewal) && Boolean.FALSE.equals(isTurnOn) ? false : null;
    }

    protected Boolean getAutoRenewalValueIfSwitchedToTrue(Boolean isAutoRenewal, Boolean isTurnOn) {
        return Boolean.FALSE.equals(isAutoRenewal) && Boolean.TRUE.equals(isTurnOn) ? true : null;
    }
}
