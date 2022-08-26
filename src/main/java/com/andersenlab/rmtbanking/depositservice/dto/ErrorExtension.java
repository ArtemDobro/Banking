package com.andersenlab.rmtbanking.depositservice.dto;

import lombok.Value;

@Value
public class ErrorExtension {
    String message;
    String errorCode;
}