package com.example.springsessionproject.domain.Item;

import lombok.Data;

@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price;

    public Item() {
    }

    public Item(String itemName, Integer price) {
        this.itemName = itemName;
        this.price = price;
    }
}