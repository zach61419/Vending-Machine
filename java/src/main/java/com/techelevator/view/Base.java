package com.techelevator.view;

public class Base {

    private String itemName;
    private int quantity;
    private double price;
    private String message;

    public Base(String itemName, int quantity, double price, String message) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.message = message;
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


}
