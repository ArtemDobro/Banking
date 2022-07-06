package com.andersenlab.rmtbanking.depositservice.controller;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsDto;
import com.andersenlab.rmtbanking.depositservice.entity.Account;
import com.andersenlab.rmtbanking.depositservice.entity.Card;
import com.andersenlab.rmtbanking.depositservice.mapper.DebitCardsMapper;
import com.andersenlab.rmtbanking.depositservice.mapper.DebitCardsMapperImpl;
import com.andersenlab.rmtbanking.depositservice.service.DebitCardService;
import com.andersenlab.rmtbanking.depositservice.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(DebitCardController.class)
@DisplayName("DebitCardController test class")
public class DebitCardControllerTest {

    @Autowired
    DebitCardController debitCardController;

    @Spy
    DebitCardsMapper debitCardsMapper = new DebitCardsMapperImpl();

    @MockBean
    private DebitCardService debitCardService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void dateFormatTest() throws Exception {
        Account account = EntityCreator.getTestAccount();
        Card card = EntityCreator.getCard();
        String clientId = account.getClientId().toString();

        List<DebitCardsDto> cardsDtos = List.of(debitCardsMapper.toDto(card));

        Mockito.when(debitCardService.getAllActiveDebitCards(clientId)).thenReturn(cardsDtos);

        mockMvc.perform
                (MockMvcRequestBuilders.get("/auth/deposit-cards/" + clientId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].expirationDate").value("2222-01-01"));
    }
}