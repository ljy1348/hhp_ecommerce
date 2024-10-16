package com.example.hhp_ecommerce.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Order extends BaseTime{
    private long id;
    private long memberId;
    @Setter
    private OrderStatus status;
    @Setter
    private int totalAmount;

    public Order(long memberId) {
        this.memberId = memberId;
        this.totalAmount = 0;
        this.status = OrderStatus.ORDER_PENDING;
    }

    public Order(long id, long memberId, OrderStatus status, int totalAmount) {
        this.memberId = memberId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.id = id;
    }

    public enum OrderStatus {
        ORDER_PENDING,
        PAYMENT_PENDING,
        PAYMENT_COMPLETED
    }
}
