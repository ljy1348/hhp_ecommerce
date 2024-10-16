package com.example.hhp_ecommerce.domain;

import lombok.Getter;

@Getter
public class Product extends BaseTime{
    private long id;
    private String name;
    private String detail;
    private int quantity;
    private int price;

    public Product(long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Product(long id, int quantity, int price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
