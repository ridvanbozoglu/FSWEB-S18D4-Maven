package com.workintech.s18d1.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BurgerException extends RuntimeException{
    private HttpStatus status;

    public BurgerException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getHttpStatus() {
        return status;
    }

    public void setHttpStatus(HttpStatus status) {
        this.status = status;
    }
}
