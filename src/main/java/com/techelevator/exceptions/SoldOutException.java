package com.techelevator.exceptions;

public class SoldOutException extends RuntimeException{

    private String message;

    public void SoldOutException (String string){
        this.message = string;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
