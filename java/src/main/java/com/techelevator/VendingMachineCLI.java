package com.techelevator;

import com.techelevator.view.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT};
    private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};

    private Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    Base base = new Base();

    public void run() {
        boolean running = true;
        NumberFormat formatter = new DecimalFormat("#0.00");
        base.setMoney(BigDecimal.valueOf(0.00));
        while (running) {
            boolean purchaseMenu = true;
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                base.printInventory();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                while (purchaseMenu) {
                    System.out.println(" ");
                    System.out.println("*****************************");
                    System.out.println("Current money provided: $" + formatter.format(base.getMoney()));
                    System.out.println("*****************************");
                    choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
                    if (choice.equals(PURCHASE_MENU_FEED_MONEY)) {
                        base.inputMoney();
                    } else if (choice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
                        base.displayMoney();
                        base.getSelection();
                    } else if (choice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
                        System.out.println("*****************");
                        System.out.println("Change due: $" + formatter.format(base.getMoney()));
                        System.out.println("*****************");
                        base.displayChange();
                        base.setMoney(BigDecimal.valueOf(0));
                        purchaseMenu = false;
                    }
                }
            } else if (choice.equals(MAIN_MENU_EXIT)) {
                running = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
