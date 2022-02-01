package com.controller;

import java.math.BigDecimal;
import java.util.List;

import com.dao.ItemDao;
import com.model.AddMoneyDTO;
import com.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ItemController {
    
    private ItemDao itemDao;

    public ItemController(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/inventory")
    public List<Item> getInventory() {
        return itemDao.getInventory();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/sub1")
    public void subtractOne(@RequestBody Item item) {
        itemDao.subOne(item.getLocation());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addMoney")
    public void addMoney(@RequestBody AddMoneyDTO money) {
        itemDao.addMoney(money.getMoney());
    }
}
