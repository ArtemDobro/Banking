package com.andersenlab.rmtbanking.depositservice.util;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsDto;
import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsInfoDto;
import com.andersenlab.rmtbanking.depositservice.dto.DepositDto;
import com.andersenlab.rmtbanking.depositservice.dto.DetailedDepositDto;
import com.andersenlab.rmtbanking.depositservice.entity.enums.CardStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class DtoCreator {

    public static final String EXAMPLE_UUID = "11111111-1111-1111-1111-1111111111dc";

    public static DebitCardsDto getDebitCardsDto() {
        return new DebitCardsDto(
                "11111111-1111-1111-1111-1111111111dc",
                "BestCard",
                "Mikhail",
                new BigDecimal("5.3"),
                CardStatus.ACTIVE,
                LocalDate.of(2025, Month.APRIL, 5),
                "Egorov",
                true,
                "Yes",
                false);
    }

    public static DebitCardsInfoDto getDebitCardsInfoDto() {
        return new DebitCardsInfoDto("account_number",
                "YES",
                "2222-01-01",
                "9999999",
                "USD",
                "EXPIRED");
    }

    public static DetailedDepositDto getDetailedDepositDto() {
        return new DetailedDepositDto("1234567890123456",
                LocalDateTime.of(2022, Month.APRIL, 1, 10, 10),
                LocalDateTime.of(2028, Month.APRIL, 1, 10, 10),
                new BigDecimal("5.3"),
                new BigDecimal("7000000"),
                true,
                "test name",
                "USD",
                "schemaName",
                true,
                true);
    }

    public static DepositDto getDepositDto() {
        return new DepositDto(
                "11111111-1111-1111-1111-1111111111dc",
                LocalDateTime.of(2020, 9, 23, 10, 10),
                LocalDateTime.of(2025, 9, 23, 10, 10),
                BigDecimal.valueOf(9999999),
                "product_name",
                "USD",
                "");
    }
}