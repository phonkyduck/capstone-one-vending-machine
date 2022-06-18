package com.techelevator;

import java.math.BigDecimal;

public class Candy extends VendingItems{

    private String vendingPhrase = "Munch Munch, Yum!";

    public Candy(String name, BigDecimal price, String itemSlot, String itemType, int itemQuantity){
        super(name, price, itemSlot, itemType, itemQuantity);
    }

    @Override
    public void vendingMessage(){
        System.out.println(vendingPhrase);
    }

}
