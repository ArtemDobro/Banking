package com.andersenlab.rmtbanking.depositservice.mapper;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsDto;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DebitCardsMapper {

    @Mapping(source = "card.account.accountNumber", target = "accountNumber")
    DebitCardsDto toDto(Card card);

    Card toEntity(DebitCardsDto debitCardsDto);

    List<DebitCardsDto> debitCardsToDebitCardsDto(List<Card> allByAccountId);
}