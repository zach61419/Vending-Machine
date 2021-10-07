package com.techelevator.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    private double balance;
    private double moneyInserted;
    private double change;
    private List<Base> items;

    public VendingMachine(double balance, double moneyInserted, double change) {
        this.balance = balance;
        this.moneyInserted = moneyInserted;
        this.change = change;
    }


    public double getBalance() {
        return balance;
    }

    public double getMoneyInserted() {
        return moneyInserted;
    }

    public void setMoneyInserted(double moneyInserted) {
        this.moneyInserted = moneyInserted;
    }

    public double getChange() {
        return change;
    }


    public double changeDue(double getPrice) {
        balance -= getPrice;
        return balance;
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

    File inventory = new File("vendingmachine.csv");
    // Scanner invList = new Scanner(inventory.toString());

    public void getInventory(File inventory, List category, List position, List price, List name) {
        try (Scanner fileScanner = new Scanner(inventory)) {
            int counter = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                try {
                    List<String> separatedList = new ArrayList<String>(Arrays.asList(line.split("\\|")));
                    if (separatedList.get(counter).equalsIgnoreCase("Chip")) {
                        List<Chips> chip = new ArrayList<Chips>();
                        Chips chips = new Chips(name.get(counter), Base.getInitialPrice(), price.get(counter), "Crunch Crunch, Yum!", category.get(counter));
                    }
                } catch (Exception e) {
                    System.out.println("No Inventory.");
                }counter++;
            }
        } catch (IOException e) {
            System.out.println("Bad input data");
        }
    }
}
