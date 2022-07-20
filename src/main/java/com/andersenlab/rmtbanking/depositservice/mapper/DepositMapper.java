package com.andersenlab.rmtbanking.depositservice.mapper;

import com.andersenlab.rmtbanking.depositservice.dto.DetailedDepositDto;
import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
}
