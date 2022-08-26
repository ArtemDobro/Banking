package com.andersenlab.rmtbanking.depositservice.validation.impl;

import com.andersenlab.rmtbanking.depositservice.validation.impl.UuidConstraint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test UUID validation")
public class UuidConstraintTest {

    private final UuidConstraint constraint = new UuidConstraint();

    @DisplayName("Test validation for UUIDs")
    @MethodSource("getUuid")
    @ParameterizedTest
    public void testUuidValidation(String uuid, boolean expected) {
        assertEquals(expected, constraint.isValid(uuid, null));
    }

    private static Stream<Arguments> getUuid() {
        return Stream.of(
                Arguments.of("12345678-abcd-ef90-1234-1234567890ab", true),
                Arguments.of("abcdefab-abcd-efef-ffff-aaabbccddeef", true),
                Arguments.of("ABCDEFAB-abcd-efef-ffff-1234567890ef", true),
                Arguments.of("12345678-abcd-ef90-1234-1234567890fp", false),
                Arguments.of("12345678-abcda-ef90-1234-1234567-890ab", false),
                Arguments.of("123-123-123-123-123", false),
                Arguments.of("12345678-abcd-ef901234-1234567890ab", false),
                Arguments.of("12345678abcd-ef90-1234-1234567890ab", false),
                Arguments.of("123456781-abcd-ef90-1234-1234567890ab", false),
                Arguments.of("1234567-abcd-ef90-1234-1234567890ab", false),
                Arguments.of("12345678-ab111-ef90-1234-1234567890ab", false),
                Arguments.of("12345678-abd-ef90-1234-1234567890ab", false),
                Arguments.of("12345678-abcd-ef901-1234-1234567890ab", false),
                Arguments.of("12345678-abcd-ef9-1234-1234567890ab", false),
                Arguments.of("12345678-abcd-ef90-12341-1234567890ab", false),
                Arguments.of("12345678-abcd-ef90-123-1234567890ab", false),
                Arguments.of("12345678-abcd-ef90-1234-1234567890ab1", false),
                Arguments.of("12345678-abcd-ef90-1234-1234567890a", false)
        );
    }
}