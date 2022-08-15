package com.andersenlab.rmtbanking.depositservice.mapper;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsDto;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import com.andersenlab.rmtbanking.depositservice.util.DtoCreator;
import com.andersenlab.rmtbanking.depositservice.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Debit cards mapper test class")
class DebitCardsMapperTest {

    private final DebitCardsMapper debitCardsMapper = new DebitCardsMapperImpl();

    @Test
    @DisplayName("Debit cards to Dto test method")
    void fromEntityToDto() {
        Card card = EntityCreator.getTestCard();
        DebitCardsDto cardDto = debitCardsMapper.toDto(card);
        compareEntityWithDto(card, cardDto);
    }

    @Test
    @DisplayName("Debit cards to Entity test method")
    void fromDtoToEntity() {
        DebitCardsDto debitCardsDto = DtoCreator.getDebitCardsDto();
        Card card = debitCardsMapper.toEntity(debitCardsDto);
        compareEntityWithDto(card, debitCardsDto);
    }

    @Test
    @DisplayName("Debit card list to list Dto's test method")
    void debitCardsToDebitCardsDto() {
        List<Card> cardList = List.of(EntityCreator.getTestCard());
        List<DebitCardsDto> cardDtos = debitCardsMapper.debitCardsToDebitCardsDto(cardList);
        compareProductListWithListDto(cardList, cardDtos);
    }

    private void compareEntityWithDto(Card card, DebitCardsDto debitCardsDto) {
        assertAll(
                () -> assertEquals(card.getId().toString(), debitCardsDto.getId()),
                () -> assertEquals(card.getCardNumber(), debitCardsDto.getCardNumber()),
                () -> assertEquals(card.getTransactionLimit(), debitCardsDto.getTransactionLimit()),
                () -> assertEquals(card.getStatus(), debitCardsDto.getStatus()),
                () -> assertEquals(card.getExpirationDate(), debitCardsDto.getExpirationDate()),
                () -> assertEquals(card.getHolderName(), debitCardsDto.getHolderName()),
                () -> assertEquals(card.isVirtualCard(), debitCardsDto.isVirtualCard()),
                () -> assertEquals(card.getDigitalWallet(), debitCardsDto.getDigitalWallet()),
                () -> assertEquals(card.isDefaults(), debitCardsDto.isDefaults())
        );
    }

    private void compareProductListWithListDto(List<Card> cardList, List<DebitCardsDto> cardDtos) {
        assertEquals(cardList.size(), cardDtos.size());
        for (int s = 0; s < cardList.size(); s++) {
            compareEntityWithDto(cardList.get(s), cardDtos.get(s));
        }
    }
}