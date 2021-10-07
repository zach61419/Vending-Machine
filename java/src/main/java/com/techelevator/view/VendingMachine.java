package com.techelevator.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    private double balance;
    private double moneyInserted;
    private double change;

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

    public double moneyInserted() {
        return moneyInserted;
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

        }
        catch (NullPointerException e) {
            System.out.println("Please enter an item to vend");
        }
        //catch (IOException e) {
            //if (userSelection)
       // }
        return "";
    }

    File inventory = new File("vendingmachine.csv");
   // Scanner invList = new Scanner(inventory.toString());

    public static String getInventory(File inventory){
        List<String> lines = new ArrayList<String>();
        List<String> position = new ArrayList<String>();
        List<String> name = new ArrayList<>();
        List<Double> price = new ArrayList<>();
      try(Scanner fileScanner = new Scanner(inventory)){
          while(fileScanner.hasNextLine()){
              lines.add(fileScanner.nextLine());
          }
      }catch (Exception e){
          System.out.println("No Inventory.");
      }
        for (String pos : lines){
            position.add(pos.substring(0,3));
        }

        return "";
    }



}
