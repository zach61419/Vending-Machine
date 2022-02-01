package com.model;

import java.math.BigDecimal;

public class Item {

    private int itemId;
    private String location;
    private String type;
    private String name;
    private BigDecimal price;
    private int inventory;

    public Item(int itemId, String location, String type, String name, BigDecimal price, int inventory) {
        this.itemId = itemId;
        this.location = location;
        this.type = type;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }

    public Item() {

    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
