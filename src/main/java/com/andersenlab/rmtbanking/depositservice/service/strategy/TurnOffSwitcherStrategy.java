package com.andersenlab.rmtbanking.depositservice.service.strategy;

import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.entity.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

@Component
public class TurnOffSwitcherStrategy extends SwitcherStrategy {

    @Override
    public Optional<Boolean> getAutoRenewal(Boolean isTurnOn, Boolean isAutoRenewal, Agreement agreement) {
        Map<Boolean, BiFunction<Boolean, Boolean, Boolean>> funcMap = getFunctionMap();
        BiFunction<Boolean, Boolean, Boolean> func = funcMap.get(isTurnOn);
        Boolean retVal = null;

        if (isActiveUntilNonNull(agreement)
                && agreement.getProduct().getActiveUntil().isAfter(agreement.getEndDate().toLocalDate())) {
            retVal = func.apply(isAutoRenewal, false);
        }

        return Optional.ofNullable(retVal);
    }

    private Boolean isActiveUntilNonNull(Agreement agreement) {
        LocalDate activeUntil = Optional.ofNullable(agreement.getProduct()).map(Product::getActiveUntil).orElse(null);
        return Objects.nonNull(activeUntil);
    }
}
