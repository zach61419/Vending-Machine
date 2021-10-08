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

    public void purchase(String selection, double money){
        int quantity = 0;
        double itemPrice = 0;
        String message = "";
        double change = 0;
        for (Map.Entry<String, Base> i : inventoryMap.entrySet()){
            if ( i.getKey().equalsIgnoreCase(selection)){
                quantity = i.getValue().getQuantity();
                itemPrice = i.getValue().getPrice();
                message = i.getValue().getMessage();

                if (money == 0){
                    System.out.println("Add Money");
                }
                else if(quantity == 0){
                    System.out.println("SOLD OUT");
                }else if(money < itemPrice) {
                    System.out.println("Not Enough Money");
                }
                else if (money >= itemPrice){
                    change = money - itemPrice;
                  // quantity --;
                   // i.setValue(i.getValue());
                    System.out.println("You picked " + selection + " for " + itemPrice + " and are owed " + change);
                    System.out.println(message);
                }
            }
        }
        
    }

}
/**
 * track quantity
 * create a 'balance' to keep track of money, so we can add more money as we go along in the program
 * round the decimals
 * log
 */
