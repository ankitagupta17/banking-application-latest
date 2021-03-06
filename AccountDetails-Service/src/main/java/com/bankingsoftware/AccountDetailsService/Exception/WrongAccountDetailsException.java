package com.bankingsoftware.AccountDetailsService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class WrongAccountDetailsException extends RuntimeException{
    public WrongAccountDetailsException(String message) {
        super(message);
    }
}
