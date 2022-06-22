package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Inventory {

    Map<String, VendingItems> itemsForSale = new TreeMap<>();

    public Map<String, VendingItems> inventoryLoader() {

        File inventoryList = new File("vendingmachine.csv");

        try (Scanner inventoryInput = new Scanner(inventoryList)) {
            while (inventoryInput.hasNextLine()) {
                String lineOfInput = inventoryInput.nextLine();
                String[] itemDetails = lineOfInput.split("\\|");

                for (String item: itemDetails) {
                    if(itemDetails[3].equals("Chip")) {
                        Chips chip = new Chips(itemDetails[1], BigDecimal.valueOf(Double.parseDouble(itemDetails[2])), itemDetails[0], itemDetails[3], 5);
                        itemsForSale.put(itemDetails[0], chip);
                    } else if(itemDetails[3].equals("Candy")) {
                        Candy candy = new Candy(itemDetails[1], BigDecimal.valueOf(Double.parseDouble(itemDetails[2])), itemDetails[0], itemDetails[3], 5);
                        itemsForSale.put(itemDetails[0], candy);
                    } else if(itemDetails[3].equals("Drink")) {
                        Beverages beverage = new Beverages(itemDetails[1], BigDecimal.valueOf(Double.parseDouble(itemDetails[2])), itemDetails[0], itemDetails[3], 5);
                        itemsForSale.put(itemDetails[0], beverage);
                    } else if(itemDetails[3].equals("Gum")) {
                        Gum gum = new Gum(itemDetails[1], BigDecimal.valueOf(Double.parseDouble(itemDetails[2])), itemDetails[0], itemDetails[3], 5);
                        itemsForSale.put(itemDetails[0], gum);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file does not exist.");
        }
        return itemsForSale;
    }

    public void displayItems(Map<String, VendingItems> itemsForSale) {
        for (Map.Entry<String, VendingItems> item : this.itemsForSale.entrySet()) {
            if (item.getValue().getItemQuantity() == 0) {
                System.out.println(item.getValue().getItemSlot() + " SOLD OUT");
            } else {
                System.out.println(item.getValue().getItemSlot() + " " + item.getValue().getName() + " " + String.format("%.2f", item.getValue().getPrice()) + ", " + item.getValue().getItemQuantity() + " remaining.");
            }
        }
    }
}
