package com.bankingsoftware.AccountProfileService.Exception;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GenericException extends  RuntimeException{
    private String message;

    public GenericException(String message) {

        this.message=message;
    }
}
