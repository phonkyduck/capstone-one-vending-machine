package com.techelevator;

import java.math.BigDecimal;

public class Chips extends VendingItems{

    private String vendingPhrase = "Crunch Crunch, Yum!";

    public Chips (String name, BigDecimal price, String itemSlot, String itemType, int itemQuantity){
        super(name, price, itemSlot, itemType, itemQuantity);
        this.setVendingPhrase(this.vendingPhrase);
    }

    @Override
    public void vendingMessage(){
        System.out.println(vendingPhrase);
    }

}
