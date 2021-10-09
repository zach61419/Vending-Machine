package com.techelevator.view;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class VendingMachine {

    private int quantity;
    private double change;
    private double money;
    private String changeDue;
    private Map<String, Base> inventoryMap;
    private double price;
    private String selection;
    private String itemName;
    private String message;


    public VendingMachine() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getChangeDue() {
        return changeDue;
    }

    public Map<String, Base> getInventoryMap() {
        return inventoryMap;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSelection() {
        return selection;
    }

    public String getItemName() {
        return itemName;
    }

    public String getMessage() {
        return message;
    }

    public double userInputMoney() {
        Scanner getMoney = new Scanner(System.in);
        System.out.println("Please enter money ");
        return Double.parseDouble(getMoney.nextLine());
    }

    public String userItemSelection() {
        String userSelection = "";
        try (Scanner getSelection = new Scanner(System.in)) {
            System.out.println("Please choose an item to vend");
            userSelection = getSelection.nextLine();
        } catch (NullPointerException e) {
            System.out.println("Not a valid item");
        }
        return userSelection;
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

    public int purchase(String selection, double money) {
        double itemPrice = 0;
        for (Map.Entry<String, Base> i : inventoryMap.entrySet()) {
            if (i.getKey().equalsIgnoreCase(selection)) {
                itemName = i.getValue().getItemName();
                quantity = i.getValue().getQuantity();
                price = i.getValue().getPrice();
                message = i.getValue().getMessage();
                if (money == 0) {
                    System.out.println("Add Money");
                } else if (quantity == 0) {
                    System.out.println("SOLD OUT");
                } else if (money < itemPrice) {
                    System.out.println("Not Enough Money");
                } else if (money >= itemPrice) {
                    i.getValue().setQuantity(quantity - 1);
                }
            }
        }
        return quantity;

    }

    public String changeDue(String selection) {
        String changeDue = "";
        double itemPrice = 0;
        int dollars = 0;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        for (Map.Entry<String, Base> i : inventoryMap.entrySet()) {
            if (i.getKey().equalsIgnoreCase(selection)) {
                itemPrice = i.getValue().getPrice();
                change = getMoney() - itemPrice;
            }
        }
        if (change >= 1) {
            while (change >= 1) {
                change--;
                dollars++;
            }
        } if (change >= 0.25) {
            while (change >= 0.25) {
                change -= 0.25;
                quarters++;
            }
        } if (change >= 0.1) {
            while (change >= 0.1) {
                change -= 0.1;
                dimes++;
            }
        } if (change >= 0.05) {
            while (change >= 0.05) {
                change -= 0.05;
                nickels++;
            }
        }
        if (dollars > 0) {
            changeDue += "Dollar bills: " + String.valueOf(dollars);
        } if (quarters > 0) {
            changeDue += ", Quarters: " + String.valueOf(quarters);
        } if (dimes > 0) {
            changeDue += ", Dimes: " + String.valueOf(dimes);
        } if (nickels > 0) {
            changeDue += ", Nickels: " + String.valueOf(nickels);
        }
        money -= itemPrice;
        return changeDue;
    }
}
/**
 * track quantity
 * create a 'balance' to keep track of money, so we can add more money as we go along in the program
 * round the decimals
 * log
 */
