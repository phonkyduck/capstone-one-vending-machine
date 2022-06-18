package com.techelevator;

import java.math.BigDecimal;

public class Gum extends VendingItems {

    private String vendingPhrase = "Chew Chew, Yum!";

    public Gum (String name, BigDecimal price, String itemSlot, String itemType, int itemQuantity){
        super(name, price, itemSlot, itemType, itemQuantity);
    }
    @Override
    public void vendingMessage(){
        System.out.println(vendingPhrase);
    }


}
