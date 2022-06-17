package com.techelevator;

import java.math.BigDecimal;

public abstract class VendingItems {

    private String name;
    private BigDecimal price;
    private String itemSlot;
    private String itemType;
    private int itemQuantity;
    private String vendingPhrase;

    //constructor
    public VendingItems(String name, BigDecimal price, String itemSlot, String itemType, int itemQuantity){
        this.name = name;
        this.price = price;
        this.itemSlot = itemSlot;
        this.itemType = itemType;
        this.itemQuantity = itemQuantity;


    }
    //getters
    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getItemSlot() { return itemSlot; }

    public String getItemType() { return itemType; }

    public int getItemQuantity() { return itemQuantity; }

    public String getVendingPhrase() { return vendingPhrase; }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setItemSlot(String itemSlot) { this.itemSlot = itemSlot; }

    public void setItemType(String itemType) { this.itemType = itemType; }

    public void setItemQuantity(int itemQuantity) { this.itemQuantity = itemQuantity; }

    public void setVendingPhrase(String vendingPhrase) { this.vendingPhrase = vendingPhrase; }
}
