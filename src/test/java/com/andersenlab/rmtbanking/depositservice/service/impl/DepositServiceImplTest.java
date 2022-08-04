package com.andersenlab.rmtbanking.depositservice.service.impl;

import com.andersenlab.rmtbanking.depositservice.dto.DepositDto;
import com.andersenlab.rmtbanking.depositservice.dto.DetailedDepositDto;
import com.andersenlab.rmtbanking.depositservice.entity.Account;
import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import com.andersenlab.rmtbanking.depositservice.mapper.DepositMapper;
import com.andersenlab.rmtbanking.depositservice.repository.AccountRepository;
import com.andersenlab.rmtbanking.depositservice.repository.AgreementRepository;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for DepositServiceImplTest")
class DepositServiceImplTest {

    private static final String EXAMPLE = "11111111-1111-1111-1111-1111111111dc";
    @Mock
    private DepositMapper depositMapper;
    @Mock
    private AgreementRepository agreementRepository;
    @Mock
    private DebitCardsRepository debitCardsRepository;
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private DepositServiceImpl depositService;

    @AfterEach
    public void clearMocks() {
        Mockito.clearInvocations(agreementRepository, debitCardsRepository, depositMapper, accountRepository);
    }

    @Test
    @DisplayName("Get deposit test method")
    void getDeposit() {
        Agreement agreement = EntityCreator.getAgreement();
        Card card = EntityCreator.getCard();
        DetailedDepositDto detailedDepositDto = DtoCreator.getDetailedDepositDto();

        when(agreementRepository.findById(UUID.fromString(DtoCreator.EXAMPLE_UUID)))
                .thenReturn(Optional.of(agreement));
        when(debitCardsRepository.findById(UUID.fromString(DtoCreator.EXAMPLE_UUID)))
                .thenReturn(Optional.of(card));
        when(depositMapper.toDetailedDepositDto(agreement, card)).thenReturn(detailedDepositDto);

        DetailedDepositDto actualDetailedDepositDto = depositService.getDetailedDeposit(DtoCreator.EXAMPLE_UUID, DtoCreator.EXAMPLE_UUID);
        assertEquals(actualDetailedDepositDto, detailedDepositDto);
    }

    @Test
    @DisplayName("Get all deposits test method")
    void getAllDeposits() {
        List<Agreement> agreements = List.of(EntityCreator.getAgreement());
        List<DepositDto> depositDtos = List.of(DtoCreator.getDepositDto());
        Account account = EntityCreator.getTestAccount();

        when(agreementRepository.getAgreementsByClientIdAndAccountStatus(UUID.fromString(EXAMPLE), true))
                .thenReturn(agreements);
        when(depositMapper.agreementsToDepositDtoList(agreements)).thenReturn(depositDtos);
        when(accountRepository.isAccountExist(UUID.fromString(EXAMPLE)))
                .thenReturn(1);

        List<DepositDto> actualDepositListDto = depositService.getAllDeposits(EXAMPLE);
        assertEquals(actualDepositListDto, depositDtos);
    }
}