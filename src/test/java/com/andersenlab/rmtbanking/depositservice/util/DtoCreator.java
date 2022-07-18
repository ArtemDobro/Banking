package com.andersenlab.rmtbanking.depositservice.util;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsDto;
import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsInfoDto;
import com.andersenlab.rmtbanking.depositservice.entity.enums.CardStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class DtoCreator {

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
}