package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {

    Map<String, String> itemName = new HashMap<>();
    Map<String, BigDecimal> itemPrices = new HashMap<>();
    Map<String, String> itemType = new HashMap<>();

    public void InventoryLoader() {

        File inventoryList = new File("vendingmachine.csv");

        try (Scanner inventoryInput = new Scanner(inventoryList)) {
            while (inventoryInput.hasNextLine()) {
                String lineOfInput = inventoryInput.nextLine();
                String[] itemDetails = lineOfInput.split("|");
                itemName.put(itemDetails[0], itemDetails[1]);
                itemPrices.put(itemDetails[0], BigDecimal.valueOf(Double.parseDouble(itemDetails[2])));
                itemType.put(itemDetails[0], itemDetails[3]);
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file does not exist.");
        }

    }

    public void displayItems(){
        for (Map.Entry<String, String> item: itemName.entrySet()) {
            System.out.println(item.getKey() + " " + item.getValue());
        }
    }
}
