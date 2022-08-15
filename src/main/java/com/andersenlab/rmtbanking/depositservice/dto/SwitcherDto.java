package com.andersenlab.rmtbanking.depositservice.dto;

import com.andersenlab.rmtbanking.depositservice.validation.annotation.Uuid;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SwitcherDto {
    @Uuid
    private String accountId;
    private Boolean isTurnOn;
}
