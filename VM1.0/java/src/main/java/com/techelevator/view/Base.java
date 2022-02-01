package com.techelevator.view;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Base {

    Menu menu = new Menu();
    private BigDecimal money;

    public Base(BigDecimal money) {
        this.money = money;
    }

    public Base() {
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public List<Item> inventory() {
        List<Item> itemList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("C:\\Users\\zachr\\Code-Repo\\Vending-Machine\\java\\vendingmachine.csv");
            CSVReader csvReader = new CSVReader(fileReader);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                Item item = new Item();
                item.setLocation(nextRecord[0]);
                item.setName(nextRecord[1]);
                item.setPrice(new BigDecimal(nextRecord[2]));
                item.setType(nextRecord[3]);
                itemList.add(item);
            }
        } catch (NullPointerException e) {
            System.out.println("The list is empty.");
        } catch (Exception i) {
            System.out.println("Something went wrong.");
        }
        return itemList;
    }

    public void printInventory() {
        List<Item> itemList = inventory();
        for (Item item : itemList) {
            System.out.println("Location: " + item.getLocation() + ", Name: " + item.getName() + ", Price: $" + item.getPrice());
        }
    }

    public void getSelection() {
        System.out.print("Enter the location of the item you wish to purchase >>> ");
        Scanner selection = new Scanner(System.in);
        String location = selection.nextLine();
        System.out.println(" ");
        List<Item> items = inventory();
        Item choice = null;
        for (Item item : items) {
            if (location.equalsIgnoreCase(item.getLocation())) {
                choice = item;
                break;
            }
        }
        if (getMoney().compareTo(choice.getPrice()) >= 0) {
            setMoney(getMoney().subtract(choice.getPrice()));
            if (choice.getType().equals("Chip")) {
                System.out.println("You chose " + choice.getName() + " for $" + choice.getPrice() + " Crunch Crunch, Yum!");
            } else if (choice.getType().equals("Candy")) {
                System.out.println("You chose " + choice.getName() + " for $" + choice.getPrice() + " Munch Munch, Yum!");
            } else if (choice.getType().equals("Drink")) {
                System.out.println("You chose " + choice.getName() + " for $" + choice.getPrice() + " Glug Glug, Yum!");
            } else if (choice.getType().equals("Gum")) {
                System.out.println("You chose " + choice.getName() + " for $" + choice.getPrice() + " Chew Chew, Yum!");
            } VendingMachineLog.log(choice.getName() + " " + choice.getLocation() + " $" + getMoney().add(choice.getPrice()) + " $" + getMoney());
        } else {
            System.out.println(" ");
            System.out.println("You did not insert enough money to purchase this item.");
        }
    }

    public void inputMoney() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        Scanner userIn = new Scanner(System.in);
        System.out.println(" ");
        System.out.print("Please enter the amount of money you are inserting >>> ");
        setMoney(getMoney().add(userIn.nextBigDecimal()));
        VendingMachineLog.log("FEED MONEY: $" + formatter.format(getMoney()));
    }

    public void displayMoney() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        System.out.println(" ");
        System.out.println("**********************");
        System.out.println("Available funds: $" + formatter.format(getMoney()));
        System.out.println("**********************");
        System.out.println(" ");
    }

    public void displayChange() {
        int dollars = 0;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;
        BigDecimal change = getMoney();
        VendingMachineLog.log("GIVE CHANGE: $" + change + " $" + change.subtract(change));
        if (change.compareTo(BigDecimal.valueOf(0)) > 0) {
            System.out.println(" ");
            System.out.print("Dispensing: ");
            while (change.compareTo(BigDecimal.valueOf(1)) >= 0) {
                change = change.subtract(BigDecimal.valueOf(1));
                dollars++;
            }
            if (dollars == 1) {
                System.out.print(dollars + " dollar ");
            }
            if (dollars > 1) {
                System.out.print(dollars + " dollars ");
            }
            while (change.compareTo(BigDecimal.valueOf(.25)) >= 0) {
                change = change.subtract(BigDecimal.valueOf(.25));
                quarters++;
            }
            if (quarters == 1) {
                System.out.print(quarters + " quarter ");
            }
            if (quarters > 1) {
                System.out.print(quarters + " quarters ");
            }
            while (change.compareTo(BigDecimal.valueOf(.1)) >= 0) {
                change = change.subtract(BigDecimal.valueOf(.1));
                dimes++;
            }
            if (dimes == 1) {
                System.out.print(dimes + " dime ");
            }
            if (dimes > 1) {
                System.out.print(dimes + " dimes ");
            }
            while (change.compareTo(BigDecimal.valueOf(.05)) >= 0) {
                change = change.subtract(BigDecimal.valueOf(.05));
                nickels++;
            }
            if (nickels == 1) {
                System.out.print(nickels + " nickel ");
            }
            if (nickels > 1) {
                System.out.print(nickels + " nickels ");
            }
            while (change.compareTo(BigDecimal.valueOf(.01)) >= 0) {
                change = change.subtract(BigDecimal.valueOf(.01));
                pennies++;
            }
            if (pennies == 1) {
                System.out.print(pennies + " penny");
            }
            if (pennies > 1) {
                System.out.print(pennies + " pennies");
            }
            System.out.println(" ");
        }
    }
}
