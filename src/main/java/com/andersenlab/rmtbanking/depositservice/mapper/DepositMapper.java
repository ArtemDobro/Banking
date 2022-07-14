package com.andersenlab.rmtbanking.depositservice.mapper;

import com.andersenlab.rmtbanking.depositservice.dto.DepositDto;
import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DebitCardsMapper.class}, imports = {Card.class})
public interface DepositMapper {

    @Mapping(source = "id", target = "agreementId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.currencyCode", target = "currencyCode")
    @Mapping(source = "account", qualifiedByName = "getDefaultCard", target = "cardId")
    DepositDto toDepositDto(Agreement agreement);

    List<DepositDto> agreementsToDepositDtoList(List<Agreement> allByAccountId);
}
