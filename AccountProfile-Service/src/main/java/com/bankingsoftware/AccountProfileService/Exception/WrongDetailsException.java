package com.bankingsoftware.AccountProfileService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongDetailsException extends RuntimeException{
    public WrongDetailsException(String message) {
        super(message);
    }
}
