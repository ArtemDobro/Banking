package com.andersenlab.rmtbanking.depositservice.controller;

import com.andersenlab.rmtbanking.depositservice.dto.DepositDto;
import com.andersenlab.rmtbanking.depositservice.dto.DetailedDepositDto;
import com.andersenlab.rmtbanking.depositservice.dto.SwitcherDto;
import com.andersenlab.rmtbanking.depositservice.service.AgreementService;
import com.andersenlab.rmtbanking.depositservice.service.DepositService;
import com.andersenlab.rmtbanking.depositservice.validation.annotation.Uuid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequestMapping("/auth/deposits")
@RestController
@RequiredArgsConstructor
public class DepositController {

    private final DepositService depositService;

    private final AgreementService service;

    /**
     * don't response anything to front, just changing variable in db
     *
     * @param switcherDto
     */
    @PatchMapping("/auto-renewal")
    @ResponseStatus(HttpStatus.OK)
    public void switchAutoRenewal(@Valid @RequestBody SwitcherDto switcherDto) {
        service.setAutoRenewal(switcherDto);
    }

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
