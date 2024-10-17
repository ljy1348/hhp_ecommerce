package com.example.hhp_ecommerce.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class OrderProduct {
    private long id;
    @Setter
    private long orderId;
    private long productId;
    private int quantity;
    private int price;

    public OrderProduct(long orderId, long productId, int quantity, int price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderProduct(long id, long orderId, long productId, int quantity, int price) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
}
