package com.techelevator.view;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.Map;
import java.util.Scanner;
import com.techelevator.view.VendingMachine;

public class MathLogic {

    private double moneyInserted;
    private int quantity; // amount of an item you want
    private double priceToPay; // how much you owe
    private String selection;
    private double balance;

    private VendingMachine vendingMachine = new VendingMachine();

    public MathLogic(){//double moneyInserted, int quantity, double priceToPay, String selection, double balance) {
//        this.selection = selection;
//        this.moneyInserted = moneyInserted;
//        this.quantity = quantity;
//        this.priceToPay = priceToPay;
//        this.balance = balance;
    }

    public double getMoneyInserted() {
        return moneyInserted;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPriceToPay() {
        return priceToPay;
    }


    /**
     *          PURCHASE METHOD
     *
     * user inserts money = moneyInserted
     * takes selection
     * checks quantity
     * gets the price x quantity = priceToPay
     * subtracts priceToPay from moneyInserted = checkChange
     * if checkChange is not zero we give them change
     * log the transaction
     */


//    public void purchase(){
//        quantity = 0;
//        double itemPrice = 0;
//        String message ="";
//
//
//
//        for (Map.Entry<String, Base> i : vendingMachine.getInventoryMap().entrySet()){
//            if ( i.getKey().equalsIgnoreCase(selection)){
//              quantity = i.getValue().getQuantity();
//              itemPrice = i.getValue().getPrice();
//              message = i.getValue().getMessage();
//            }
//        }
//
//
//        if (moneyInserted == 0){
//            System.out.println("Add Money");
//        }
//        else if(quantity == 0){
//            System.out.println("SOLD OUT");
//        }else if(moneyInserted < priceToPay) {
//            System.out.println("Not Enough Money");
//        }
//        else if (moneyInserted >= priceToPay){
//            balance = moneyInserted - priceToPay;
//            //System.out.println("You picked " + selection + " for " + itemPrice + " and are owed " + balance);
//            System.out.println(message);
//        }
//
//
//    }




}
