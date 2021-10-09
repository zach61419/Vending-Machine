package com.techelevator;

import com.techelevator.view.Base;
import com.techelevator.view.MathLogic;
import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachine;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT};
    private VendingMachine vendingMachine = new VendingMachine();
    private MathLogic mathLogic = new MathLogic();

    private Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        vendingMachine.loadInventory();
        boolean running = true;
        while (running) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                for (Map.Entry<String, Base> inventoryPair : vendingMachine.getInventoryMap().entrySet()) {
                    System.out.println(inventoryPair.getValue().getPosition() + ") " + inventoryPair.getValue().getItemName() + " | "
                            + inventoryPair.getValue().getQuantity() + " remaining | Price: " + inventoryPair.getValue().getPrice());
                }
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                System.out.println();
                double moneyInserted = vendingMachine.userInputMoney();
                String selection = vendingMachine.userItemSelection();
                if (moneyInserted < vendingMachine.getPrice()) {
                    System.out.println("Not enough money to purchase.");
                }
                if (moneyInserted >= vendingMachine.getPrice()) {
                    vendingMachine.purchase(selection, vendingMachine.getMoney());
                    System.out.println("You picked " + vendingMachine.getItemName() + " for " + vendingMachine.getPrice() + " and are owed " + vendingMachine.changeDue(selection));
                    System.out.println(vendingMachine.getMessage());
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
