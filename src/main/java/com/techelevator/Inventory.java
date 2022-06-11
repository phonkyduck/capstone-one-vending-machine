package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {

    public static void main(String[] args) throws FileNotFoundException {

        File inventoryLoader = new File("vendingmachine.csv");

        Map<String, String> itemName = new HashMap<>();
        Map<String, Double> itemPrices = new HashMap<>();
        Map<String, String> itemType = new HashMap<>();

        try (Scanner inventoryInput = new Scanner(inventoryLoader)) {
            while (inventoryInput.hasNextLine()) {
                String lineOfInput = inventoryInput.nextLine();
                String[] itemDetails = lineOfInput.split("|");
                itemName.put(itemDetails[0], itemDetails[1]);
                itemPrices.put(itemDetails[0], Double.parseDouble(itemDetails[2]));
                itemType.put(itemDetails[0], itemDetails[3]);
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file does not exist.");
        }

        public void displayItems(){

            for (Map.Entry<String,String> item: itemName.entrySet()) {
                System.out.println(item.getKey() + " " + item.getValue());
            }
        }
    }
}
