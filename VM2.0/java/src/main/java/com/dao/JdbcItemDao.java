package com.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.model.Item;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class JdbcItemDao implements ItemDao {
    
    private JdbcTemplate jdbcTemplate;

    public JdbcItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Item> getInventory() {
        List<Item> inventory = new ArrayList<>();
        String sql = "SELECT item_id, location, name, price, type, inventory FROM items;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Item item = mapRowToItem(results);
            inventory.add(item);
        }
        return inventory;
    }

    @Override
    public boolean subOne(String location) {
        String sql = "UPDATE items SET inventory = inventory - 1 WHERE location = ?;";
        jdbcTemplate.update(sql, location);
        return true;
    }

    @Override
    public void addMoney(BigDecimal money) {
        String sql = "UPDATE money SET money = money + ?;";
        jdbcTemplate.update(sql, money);
    }

    private  Item mapRowToItem(SqlRowSet results) {
        Item item = new Item();
        item.setItemId(results.getInt("item_id"));
        item.setLocation(results.getString("location"));
        item.setName(results.getString("name"));
        item.setPrice(results.getBigDecimal("price"));
        item.setType(results.getString("type"));
        item.setInventory(results.getInt("inventory"));
        return item;
    }
}