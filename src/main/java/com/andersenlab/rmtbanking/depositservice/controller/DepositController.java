package com.andersenlab.rmtbanking.depositservice.controller;


import com.andersenlab.rmtbanking.depositservice.dto.SwitcherDto;
import com.andersenlab.rmtbanking.depositservice.service.AgreementService;
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
}
