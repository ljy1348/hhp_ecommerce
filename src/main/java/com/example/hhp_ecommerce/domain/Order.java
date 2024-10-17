package com.example.hhp_ecommerce.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Order extends BaseTime{
    private long id;
    private long memberId;
    private OrderStatus status;
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

    public Order(long id, long memberId, OrderStatus status, int totalAmount, LocalDateTime createDate, LocalDateTime updateDate) {
        this.memberId = memberId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.id = id;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public void updateTotalAmount(int newAmount) {
        if (newAmount < 0) {
            throw new IllegalArgumentException("Total amount cannot be negative");
        }
        this.totalAmount = newAmount;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public enum OrderStatus {
        ORDER_PENDING,
        PAYMENT_PENDING,
        PAYMENT_COMPLETED
    }
}
