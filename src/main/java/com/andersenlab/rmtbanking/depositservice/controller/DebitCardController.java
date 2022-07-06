package com.andersenlab.rmtbanking.depositservice.controller;

import com.andersenlab.rmtbanking.depositservice.dto.DebitCardsDto;
import com.andersenlab.rmtbanking.depositservice.service.DebitCardService;
import com.andersenlab.rmtbanking.depositservice.validation.annotation.Uuid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/auth/deposit-cards")
@RestController
@RequiredArgsConstructor
public class DebitCardController {

    private final DebitCardService debitCardService;

    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DebitCardsDto> getAllDebitCards(@Uuid @PathVariable("clientId") String clientId) {
        return debitCardService.getAllActiveDebitCards(clientId);
    }
}