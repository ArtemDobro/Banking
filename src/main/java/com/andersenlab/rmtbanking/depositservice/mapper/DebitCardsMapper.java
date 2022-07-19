package com.andersenlab.rmtbanking.depositservice.mapper;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsDto;
import com.andersenlab.rmtbanking.depositservice.entity.Account;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DebitCardsMapper {

    @Mapping(source = "card.account.accountNumber", target = "accountNumber")
    DebitCardsDto toDto(Card card);

    Card toEntity(DebitCardsDto debitCardsDto);

    List<DebitCardsDto> debitCardsToDebitCardsDto(List<Card> allByAccountId);

    default String getDefaultCard(Account account) {
        return account.getCards()
                .stream()
                .filter(Card::isDefaults)
                .findFirst()
                .map(Card::getId)
                .map(UUID::toString)
                .orElse("");
    }
}