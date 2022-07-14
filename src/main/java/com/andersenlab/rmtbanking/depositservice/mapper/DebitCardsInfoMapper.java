package com.andersenlab.rmtbanking.depositservice.mapper;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsInfoDto;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DebitCardsInfoMapper {
    @Mapping(source = "account.accountNumber", target = "accountNumber")
    @Mapping(source = "account.salaryProject", target = "salaryProject")
    @Mapping(source = "account.currentBalance", target = "currentBalance")
    @Mapping(source = "account.currencyCode", target = "currencyCode")
    DebitCardsInfoDto debitCardsInfoToDto(Card card);
}