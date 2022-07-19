package com.andersenlab.rmtbanking.depositservice.mapper;

import com.andersenlab.rmtbanking.depositservice.dto.DepositDto;
import com.andersenlab.rmtbanking.depositservice.entity.Account;
import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DepositMapper {

    @Mapping(source = "id", target = "agreementId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.currencyCode", target = "currencyCode")
    @Mapping(source = "account", qualifiedByName = "getDefaultCard", target = "cardId")
    DepositDto toDepositDto(Agreement agreement);

    List<DepositDto> agreementsToDepositDtoList(List<Agreement> allByAccountId);

    @Named("getDefaultCard")
    default String getDefaultDebitCard(Account account) {
        return account.getCards()
                .stream()
                .filter(Card::isDefaults)
                .findFirst()
                .map(Card::getId)
                .map(UUID::toString)
                .orElse("");
    }
}
