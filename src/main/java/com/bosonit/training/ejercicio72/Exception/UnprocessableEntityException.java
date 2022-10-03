package com.bosonit.training.ejercicio72.Exception;

import org.springframework.http.HttpStatus;
import java.util.Date;

public class UnprocessableEntityException extends RuntimeException {

    Date timeStamp;
    private HttpStatus httpStatus;

    public UnprocessableEntityException(String message, HttpStatus httpStatus, Date timeStamp) {
        super(message);


    }
}
