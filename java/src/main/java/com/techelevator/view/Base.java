package com.techelevator.view;

public class Base {

    private String itemName;
    private int quantity;
    private double price;
    private String message;
    private String position;
    private static final int INITIAL_PRICE = 5;

    public Base(String itemName, int quantity, double price, String message, String position) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.message = message;
        this.position = position;
    }

    public Base() {

    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getMessage() {
        return message;
    }

    public String getPosition() {
        return position;
    }

    public static int getInitialPrice() {
        return INITIAL_PRICE;
    }
}
