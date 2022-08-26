package com.andersenlab.rmtbanking.depositservice.service.impl;

import com.andersenlab.rmtbanking.depositservice.dto.CardAfterOpeningDto;
import com.andersenlab.rmtbanking.depositservice.dto.CreateNewCardDto;
import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsDto;
import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsInfoDto;
import com.andersenlab.rmtbanking.depositservice.entity.CardProduct;
import com.andersenlab.rmtbanking.depositservice.mapper.DebitCardsMapper;
import com.andersenlab.rmtbanking.depositservice.repository.AccountRepository;
import com.andersenlab.rmtbanking.depositservice.repository.CardProductRepository;
import com.andersenlab.rmtbanking.depositservice.repository.DebitCardsRepository;
import com.andersenlab.rmtbanking.depositservice.service.DebitCardService;
import com.andersenlab.rmtbanking.depositservice.service.exeption.CardProductNotFound;
import com.andersenlab.rmtbanking.depositservice.service.exeption.ClientNotFoundException;
import com.andersenlab.rmtbanking.depositservice.service.exeption.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DebitCardsServiceImpl implements DebitCardService {
    private final DebitCardsRepository debitCardsRepository;
    private final AccountRepository accountRepository;
    private final CardProductRepository cardProductRepository;
    private final DebitCardsMapper debitCardsMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DebitCardsDto> getAllActiveDebitCards(String clientId) {
        log.info("Get list of debit cards for client with id {}", clientId);
        UUID uuid = UUID.fromString(clientId);
        accountRepository.findByClientId(uuid)
                .orElseThrow(() -> new ClientNotFoundException(ErrorMessage.CLIENT_STATUS));
        return debitCardsMapper.debitCardsToDebitCardsDto(debitCardsRepository.getCardsByAccountClientIdAndAccountActive
                (uuid, true));
    }

    @Override
    @Transactional(readOnly = true)
    public DebitCardsInfoDto getOneDebitCardInfo(String cardId) {
        log.info("Get information about debit card with id {}", cardId);
        return debitCardsMapper.debitCardsInfoToDto(debitCardsRepository.findById(UUID.fromString(cardId)).orElseThrow());
    }

    @Override
    public CardAfterOpeningDto orderNewCardByIdProduct(String clientId, CreateNewCardDto createNewCardDto) {
        CardProduct cardProduct = checkIdProduct(Long.getLong(createNewCardDto.getIdProduct()));

    }

    private CardProduct checkIdProduct(Long idProduct) {
        return cardProductRepository.findCardProductById(idProduct).orElseThrow(
                () -> new CardProductNotFound(ErrorMessage.CARD_PRODUCT_NOT_FOUND)
        );
    }
}