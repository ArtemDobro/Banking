package com.andersenlab.rmtbanking.depositservice.controller;

import com.andersenlab.rmtbanking.depositservice.dto.DetailedDepositDto;
import com.andersenlab.rmtbanking.depositservice.dto.SwitcherDto;
import com.andersenlab.rmtbanking.depositservice.service.DepositService;
import com.andersenlab.rmtbanking.depositservice.service.AgreementService;
import com.andersenlab.rmtbanking.depositservice.validation.annotation.Uuid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequestMapping("/auth/deposits")
@RestController
@RequiredArgsConstructor
public class DepositController {

    private final DepositService depositService;

    private final AgreementService service;
    /**
     * don't response anything to front, just changing variable in db
     * @param switcherDto
     */
    @PatchMapping("/auto-renewal")
    @ResponseStatus(HttpStatus.OK)
    public void switchAutoRenewal(@Valid @RequestBody SwitcherDto switcherDto) {
        service.setAutoRenewal(switcherDto);
    }

    @GetMapping("/{agreementId}")
    @GetMapping("/info/{agreementId}")
    @ResponseStatus(HttpStatus.OK)
    public DetailedDepositDto getDeposit(@Uuid @PathVariable("agreementId") String agreementId, @Uuid @RequestParam String cardId) {
        return depositService.getDetailedDeposit(agreementId, cardId);
    }

    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DepositDto> getAllActiveDeposits(@Uuid @PathVariable("clientId") String clientId) {
        return depositService.getAllDeposits(clientId);
    }
}
