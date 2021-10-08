package com.techelevator.view;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class VendingMachine {


    private Map<String, Base> inventoryMap;

    public VendingMachine() {

    }

    public Map<String, Base> getInventoryMap() {
        return inventoryMap;
    }

    public double getUserInputMoney() {
        Scanner getMoney = new Scanner(System.in);
        double i = Double.parseDouble(getMoney.toString());
        System.out.println("Please enter money ");
        double moneyGiven = i;
        return moneyGiven;
    }

    public String getUserItemSelection() {
        String userSelection = "";
        try (Scanner getSelection = new Scanner(System.in)) {

        } catch (NullPointerException e) {
            System.out.println("Please enter an item to vend");
        }
        return "";
    }


    public void loadInventory() {
        File inventory = new File("vendingmachine.csv");
        try (Scanner fileScanner = new Scanner(inventory)) {
            int counter = 0;
            inventoryMap = new HashMap<String, Base>();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                try {
                    List<String> separatedList = new ArrayList<String>(Arrays.asList(line.split("\\|")));
                    if (separatedList.get(3).equalsIgnoreCase("Chip")) {
                        Chips chip = new Chips(separatedList.get(1), 5, Double.parseDouble(separatedList.get(2)), "Crunch Crunch, Yum!", separatedList.get(0));
                        inventoryMap.put(separatedList.get(0), chip);
                    }
                    if (separatedList.get(3).equalsIgnoreCase("Candy")) {
                        Candy candy = new Candy(separatedList.get(1), 5, Double.parseDouble(separatedList.get(2)), "Munch Munch, Yum!", separatedList.get(0));
                        inventoryMap.put(separatedList.get(0), candy);
                    }
                    if (separatedList.get(3).equalsIgnoreCase("Drink")) {
                        Drinks drink = new Drinks(separatedList.get(1), 5, Double.parseDouble(separatedList.get(2)), "Glug Glug, Yum!", separatedList.get(0));
                        inventoryMap.put(separatedList.get(0), drink);
                    }
                    if (separatedList.get(3).equalsIgnoreCase("Gum")) {
                        Gum gum = new Gum(separatedList.get(1), 5, Double.parseDouble(separatedList.get(2)), "Chew Chew, Yum!", separatedList.get(0));
                        inventoryMap.put(separatedList.get(0), gum);
                    }
                } catch (Exception e) {
                    System.out.println("No Inventory.");
                }
                counter++;
            }
        } catch (IOException e) {
            System.out.println("Bad input data");
        }
    }
}
