package com.andersenlab.rmtbanking.depositservice.mapper;

import com.andersenlab.rmtbanking.depositservice.dto.CardProductDto;
import com.andersenlab.rmtbanking.depositservice.entity.CardProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardProductMapper {

    CardProductDto toDto(CardProduct cardProduct);

    List<CardProductDto> cardProductsToCardProductDtos(List<CardProduct> cardProducts);
}
