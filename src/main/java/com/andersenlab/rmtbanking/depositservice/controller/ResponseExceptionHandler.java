package com.andersenlab.rmtbanking.depositservice.controller;

import com.andersenlab.rmtbanking.depositservice.dto.ErrorExtension;
import com.andersenlab.rmtbanking.depositservice.dto.ErrorResponse;
import com.andersenlab.rmtbanking.depositservice.service.exeption.ErrorCode;
import com.andersenlab.rmtbanking.depositservice.service.exeption.ClientNotFoundException;
import com.andersenlab.rmtbanking.depositservice.service.exeption.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.validation.ConstraintViolationException;
import java.net.ConnectException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleClientNotFoundException(Exception ex) {
        ErrorExtension body = new ErrorExtension(
                ex.getMessage(),
                ErrorCode.CLIENT_NOT_FOUND);
        return new ResponseEntity<>(body, NOT_FOUND);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ErrorExtension> handleConnectException() {
        ErrorExtension body = new ErrorExtension(
                ErrorMessage.TEMPORARY_UNAVAILABLE,
                SERVICE_UNAVAILABLE.name().toLowerCase());
        return new ResponseEntity<>(body, SERVICE_UNAVAILABLE);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<ErrorExtension> errorExtensions = exception.getFieldErrors()
                .stream()
                .map(filedError -> new ErrorExtension(filedError.getDefaultMessage(),
                        String.format("invalid_%s", filedError.getField())))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.VALIDATION_FAILED, errorExtensions), BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        List<ErrorExtension> extensions = exception.getConstraintViolations()
                .stream()
                .map(violation -> new ErrorExtension(violation.getMessage(), ErrorCode.INVALID_PATH_VARIABLE))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.VALIDATION_FAILED, extensions), BAD_REQUEST);
    }
}