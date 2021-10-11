package com.techelevator;

import com.techelevator.view.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT};
    private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};
    private VendingMachine vendingMachine = new VendingMachine();

    private Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        vendingMachine.loadInventory();
        boolean running = true;
        NumberFormat formatter = new DecimalFormat("#0.00");
        while (running) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                for (Map.Entry<String, Base> inventoryPair : vendingMachine.getInventoryMap().entrySet()) {
                    if (inventoryPair.getValue().getQuantity() == 0) {
                        System.out.println(inventoryPair.getValue().getPosition() + ") " + inventoryPair.getValue().getItemName() + " | SOLD OUT | Price: " + inventoryPair.getValue().getPrice());
                    } else {
                        System.out.println(inventoryPair.getValue().getPosition() + ") " + inventoryPair.getValue().getItemName() + " | "
                                + inventoryPair.getValue().getQuantity() + " remaining | Price: " + inventoryPair.getValue().getPrice());
                    }
                }
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                boolean going = true;
                String selection = "";
                while (going) {
                    String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
                    System.out.println();
                    double moneyInserted = 0;
                    System.out.println("Current Money Provided: \\$" + formatter.format(vendingMachine.getMoney()));
                    if (purchaseChoice.equals(PURCHASE_MENU_FEED_MONEY)) {
                        moneyInserted = vendingMachine.userInputMoney();
                        VendingMachineLog.log("FEED MONEY: \\$" + formatter.format(moneyInserted) + " \\$" + formatter.format(vendingMachine.getMoney()));
                    } else if (purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
                        selection = vendingMachine.userItemSelection();
                        vendingMachine.purchase(selection);
                        if (!vendingMachine.getInventoryMap().containsKey(selection.toUpperCase())) {
                            System.out.println("Invalid input");
                        } else if (vendingMachine.getQuantity() == 0) {
                            System.out.println("SOLD OUT");
                        } else if (vendingMachine.getMoney() < vendingMachine.getPrice()) {
                            System.out.println("Not enough money");
                        } else if (vendingMachine.getMoney() >= vendingMachine.getPrice()) {
                            vendingMachine.changeDue(selection);
                            System.out.println("You picked " + vendingMachine.getItemName() + " for " + formatter.format(vendingMachine.getPrice()) + " and are owed " + vendingMachine.getChangeDue());
                            System.out.println(vendingMachine.getMessage());
                            VendingMachineLog.log(vendingMachine.getItemName() + " " + selection + " \\$" + formatter.format(vendingMachine.getMoney() + vendingMachine.getPrice()) + " \\$" + formatter.format(vendingMachine.getMoney()));
                        }
                    } else if (purchaseChoice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
                        vendingMachine.setChange(0);
                        VendingMachineLog.log("GIVE CHANGE: \\$" + formatter.format(vendingMachine.getMoney()) + " \\$0.00");
                        VendingMachineLog.log("\\,\\,\\");
                        vendingMachine.setMoney(0);
                        going = false;
                    }
                }
            } else if (choice.equals(MAIN_MENU_EXIT)) {
                running = false;
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
