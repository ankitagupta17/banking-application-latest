package com.bankingsoftware.AccountDetailsService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomiseddResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(WrongAccountDetailsException.class)
    public ResponseEntity<Object> handleExceptions(WrongAccountDetailsException exception, WebRequest webRequest)
    {
        ExceptionResponse response=new ExceptionResponse();
        response.setMessage(exception.getMessage());
        ResponseEntity<Object> entity=new ResponseEntity<>(response.getMessage(), HttpStatus.FORBIDDEN);
        return entity;
    }
}
