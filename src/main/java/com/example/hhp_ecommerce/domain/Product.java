package com.example.hhp_ecommerce.domain;

import lombok.Getter;
import lombok.Setter;

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

    public Product(long id, String detail) {
        this.id = id;
        this.detail = detail;
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

    public Product(long id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(long id, String name, String detail, int quantity, int price) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.quantity = quantity;
        this.price = price;
    }

    public void updateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("상품 수량이 부족합니다.");
        }
        this.quantity = quantity;
    }
}
