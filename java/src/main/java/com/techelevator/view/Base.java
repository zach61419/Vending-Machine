package com.techelevator.view;

public class Base {

    private String itemName;
    private int quantity = 5;
    private double price;
    private String message;
    private String position;

    public Base(String itemName, int quantity, double price, String message, String position) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.message = message;
        this.position = position;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
