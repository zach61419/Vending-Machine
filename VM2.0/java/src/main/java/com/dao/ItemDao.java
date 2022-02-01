package com.dao;

import java.math.BigDecimal;
import java.util.List;

import com.model.Item;

public interface ItemDao {

    List<Item> getInventory();

    boolean subOne(String location);

    void addMoney(BigDecimal money);
}