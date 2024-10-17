package com.example.hhp_ecommerce.domain;

import lombok.Getter;

@Getter
public class Payment extends BaseTime{
    private long id;
    private long userId;
    private long orderId;
    private int amount;

    public Payment (long userId, long orderId, int amount) {
        this.amount = amount;
        this.userId = userId;
        this.orderId = orderId;
    }

    public Payment (long id, long userId, long orderId, int amount) {
        this.id = id;
        this.amount = amount;
        this.userId = userId;
        this.orderId = orderId;
    }
}
