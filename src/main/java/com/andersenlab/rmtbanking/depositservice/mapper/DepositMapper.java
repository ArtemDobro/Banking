package com.andersenlab.rmtbanking.depositservice.mapper;

import com.andersenlab.rmtbanking.depositservice.dto.DepositDto;
import com.andersenlab.rmtbanking.depositservice.dto.DetailedDepositDto;
import com.andersenlab.rmtbanking.depositservice.entity.Account;
import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DepositMapper {

    @Mapping(source = "card.cardNumber", target = "cardNumber")
    @Mapping(source = "agreement.startDate", target = "startDate")
    @Mapping(source = "agreement.endDate", target = "endDate")
    @Mapping(source = "agreement.interestRate", target = "interestRate")
    @Mapping(source = "agreement.currentBalance", target = "currentBalance")
    @Mapping(source = "agreement.autoRenewal", target = "autoRenewal")
    @Mapping(source = "agreement.product.name", target = "name")
    @Mapping(source = "agreement.product.currencyCode", target = "currencyCode")
    @Mapping(source = "agreement.product.schemaName", target = "schemaName")
    @Mapping(source = "agreement.product.capitalization", target = "isCapitalization")
    @Mapping(source = "agreement.product.revocable", target = "isRevocable")
    DetailedDepositDto toDetailedDepositDto(Agreement agreement, Card card);

    @Mapping(source = "id", target = "agreementId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.currencyCode", target = "currencyCode")
    @Mapping(source = "account", qualifiedByName = "getDefaultCard", target = "cardId")
    DepositDto toDepositDto(Agreement agreement);

    List<DepositDto> agreementsToDepositDtoList(List<Agreement> allByAccountId);

    @Named("getDefaultCard")
    default String getDefaultDebitCard(Account account) {

        return Optional.ofNullable(account.getCards())
                .stream()
                .flatMap(Collection::stream)
                .filter(Card::isDefaults)
                .findFirst()
                .map(Card::getId)
                .map(UUID::toString)
                .orElse("");
    }
}