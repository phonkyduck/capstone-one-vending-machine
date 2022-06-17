package com.techelevator.exceptions;

public class UnacceptableBillException extends RuntimeException {

    private String message;

    public UnacceptableBillException(String string){
        this.message = string;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
