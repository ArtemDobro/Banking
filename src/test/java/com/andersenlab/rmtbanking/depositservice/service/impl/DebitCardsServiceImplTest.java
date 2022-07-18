package com.andersenlab.rmtbanking.depositservice.service.impl;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsDto;
import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsInfoDto;
import com.andersenlab.rmtbanking.depositservice.entity.Account;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import com.andersenlab.rmtbanking.depositservice.mapper.DebitCardsMapper;
import com.andersenlab.rmtbanking.depositservice.repository.AccountRepository;
import com.andersenlab.rmtbanking.depositservice.repository.DebitCardsRepository;
import com.andersenlab.rmtbanking.depositservice.util.DtoCreator;
import com.andersenlab.rmtbanking.depositservice.util.EntityCreator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for DebitCardsServiceImpl")
class DebitCardsServiceImplTest {

    private static final String EXAMPLE = "11111111-1111-1111-1111-1111111111dc";
    @Mock
    private DebitCardsMapper debitCardsMapper;
    @Mock
    private DebitCardsRepository debitCardsRepository;
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private DebitCardsServiceImpl debitCardsService;

    @AfterEach
    public void clearMocks() {
        Mockito.clearInvocations(debitCardsRepository, debitCardsMapper);
    }

    @Test
    @DisplayName("Get all active debit cards test method")
    public void getAllActiveDebitCards() {
        List<Card> cards = List.of(EntityCreator.getCard());
        List<DebitCardsDto> cardsDtos = List.of(DtoCreator.getDebitCardsDto());
        Account account = EntityCreator.getTestAccount();

        when(debitCardsRepository.getCardsByAccountClientIdAndAccountActive(UUID.fromString(EXAMPLE), true)).thenReturn(cards);
        when(debitCardsMapper.debitCardsToDebitCardsDto(cards)).thenReturn(cardsDtos);
        when(accountRepository.findByClientId(UUID.fromString(EXAMPLE))).thenReturn(Optional.of(account));

        List<DebitCardsDto> actualCardListDto = debitCardsService.getAllActiveDebitCards(EXAMPLE);
        assertEquals(actualCardListDto, cardsDtos);
    }

    @Test
    void getOneDebitCardInfo() {
        Card card = EntityCreator.getCard();
        when(debitCardsRepository.findById(card.getId())).thenReturn(Optional.of(card));
        DebitCardsInfoDto expected = DtoCreator.getDebitCardsInfoDto();
        when(debitCardsMapper.debitCardsInfoToDto(card)).thenReturn(expected);
        DebitCardsInfoDto actual = debitCardsService.getOneDebitCardInfo(String.valueOf(card.getId()));
        assertEquals(expected, actual);
    }

    @Test
    void throwExceptionIfCardIsEmpty() {
        Card card = new Card();
        assertThrows(RuntimeException.class, () -> debitCardsService.getOneDebitCardInfo(String.valueOf(card.getId())));
    }
}