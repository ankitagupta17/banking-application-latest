package com.bankingsoftware.AccountProfileService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(WrongDetailsException.class)
    public ResponseEntity<Object> handleExceptions(WrongDetailsException exception, WebRequest webRequest)
    {
        GenericException response=new GenericException();
        logger.info("exception--------");
        response.setMessage(exception.getMessage());
        return new ResponseEntity(response.getMessage(), HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
//        return entity;
    }
}
