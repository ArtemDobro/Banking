package com.andersenlab.rmtbanking.depositservice.service.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SwitcherStrategyPicker {
    private final SwitcherStrategy turnOffSwitcherStrategy;
    private final SwitcherStrategy turnOnSwitcherStrategy;

    public SwitcherStrategy getSwitcherStrategy(boolean value) {
        Map<Boolean, SwitcherStrategy> switcherStrategyMap = getSwitcherStrategyMap();
        return switcherStrategyMap.get(value);
    }

    private Map<Boolean, SwitcherStrategy> getSwitcherStrategyMap() {
        return Map.ofEntries(
                new AbstractMap.SimpleEntry<>(true, turnOnSwitcherStrategy),
                new AbstractMap.SimpleImmutableEntry<>(false, turnOffSwitcherStrategy)
        );
    }
}
