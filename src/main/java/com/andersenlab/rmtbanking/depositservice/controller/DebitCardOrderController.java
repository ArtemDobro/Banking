package com.andersenlab.rmtbanking.depositservice.controller;

import com.andersenlab.rmtbanking.depositservice.dto.CardAfterOpeningDto;
import com.andersenlab.rmtbanking.depositservice.dto.CreateNewCardDto;
import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsDto;
import com.andersenlab.rmtbanking.depositservice.service.DebitCardService;
import com.andersenlab.rmtbanking.depositservice.validation.annotation.Uuid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("auth/deposit-card-orders")
@RestController
@RequiredArgsConstructor
public class DebitCardOrderController {
    private final DebitCardService debitCardService;

    @PostMapping("/new/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public CardAfterOpeningDto orderNewCardByIdProduct(@Uuid @PathVariable("clientId") String clientId, @RequestBody CreateNewCardDto createNewCardDto) {
        return debitCardService.orderNewCardByIdProduct(clientId,createNewCardDto);
    }

}
