package com.bankingsoftware.AccountDetailsService.Exception;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExceptionResponse extends  RuntimeException{
    private String message;

    public ExceptionResponse(String message) {
        super(message);
    }
}
