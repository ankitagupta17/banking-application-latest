package com.bankingsoftware.AccountProfileService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CHECKPOINT)
public class AccountProfileNotFoundException extends RuntimeException{
    public AccountProfileNotFoundException(String message) {
        super(message);
    }
}
