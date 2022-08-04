package com.andersenlab.rmtbanking.depositservice.service.util;

import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.UUID;

@UtilityClass
public class UuidUtil {

    public Optional<UUID> UuidFromString(String value) {
        try {
            return Optional.of(UUID.fromString(value));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
