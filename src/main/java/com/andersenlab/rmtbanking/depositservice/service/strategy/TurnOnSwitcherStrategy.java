package com.andersenlab.rmtbanking.depositservice.service.strategy;

import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.entity.Product;
import com.andersenlab.rmtbanking.depositservice.repository.AgreementsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class TurnOnSwitcherStrategy extends SwitcherStrategy {
    private final AgreementsRepository repository;

    @Override
    public Optional<Boolean> getAutoRenewal(Boolean isTurnOn, Boolean isAutoRenewal, Agreement agreement) {
        boolean productIsActiveAfterAgreementEndDate = agreement.getProduct().getActiveUntil().isAfter(agreement.getEndDate().toLocalDate());
        Map<Boolean, BiFunction<Boolean, Boolean, Boolean>> funcMap = getFunctionMap();
        BiFunction<Boolean, Boolean, Boolean> func = funcMap.get(isTurnOn);
        Boolean retVal = null;

        if (Boolean.FALSE.equals(agreement.getProduct().isActive())) {
            retVal = false;
        }

        if (isLastDay(agreement.getEndDate(), LocalDateTime.now())) {
            agreement.setEndDate(extendTheDeposit(agreement.getStartDate(), agreement.getEndDate()));
            repository.save(agreement);
        }

        if (isProductFieldsNonNull(agreement)
                && productIsActiveAfterAgreementEndDate) {
            retVal = func.apply(isAutoRenewal, true);
        }

        return Optional.ofNullable(retVal);
    }

    private Boolean isLastDay(LocalDateTime endDate, LocalDateTime currentDate) {
        return endDate.getYear() == currentDate.getYear()
                && endDate.getMonth() == currentDate.getMonth()
                && endDate.getDayOfMonth() == currentDate.getDayOfMonth();
    }

    private LocalDateTime extendTheDeposit(LocalDateTime startDate, LocalDateTime endDate) {
        Duration duration = Duration.between(startDate, endDate);
        return endDate.plus(duration);
    }

    private Boolean isProductFieldsNonNull(Agreement agreement) {
        LocalDate activeUntil = Optional.ofNullable(agreement.getProduct()).map(Product::getActiveUntil).orElse(null);
        LocalDate activeSince = Optional.ofNullable(agreement.getProduct()).map(Product::getActiveSince).orElse(null);

        return Objects.nonNull(activeSince) && Objects.nonNull(activeUntil);
    }
}
