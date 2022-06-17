package com.techelevator.exceptions;

import java.math.BigDecimal;

public class NotFullyPaidException extends RuntimeException {

    private String message;
    private BigDecimal remaining;

    public NotFullyPaidException (String message, BigDecimal remaining){
        this.message = message;
        this.remaining = remaining;
    }

    public BigDecimal getRemaining(){
        return remaining;
    }
    @Override
    public String getMessage(){
        return message + remaining;
    }
}
