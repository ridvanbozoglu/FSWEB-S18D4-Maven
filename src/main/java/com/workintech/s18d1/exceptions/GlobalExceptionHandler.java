package com.workintech.s18d1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BurgerException.class)
    public ResponseEntity<BurgerErrorResponse> zooErrorExceptionHandler(BurgerException burgerErrorException){
        BurgerErrorResponse zooErrorResponse = new BurgerErrorResponse(burgerErrorException.getMessage());
        return new ResponseEntity<>(zooErrorResponse,burgerErrorException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> zooErrorExceptionHandler(Exception exception){
        BurgerErrorResponse errorResponse = new BurgerErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
