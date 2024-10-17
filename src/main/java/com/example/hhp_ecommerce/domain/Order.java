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
        validateMemberId(memberId);
        this.memberId = memberId;
        this.totalAmount = 0;
        this.status = OrderStatus.ORDER_PENDING;
    }

    public Order(long id, long memberId, OrderStatus status, int totalAmount) {
        validateMemberId(memberId);
        validateTotalAmount(totalAmount);
        this.memberId = memberId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.id = id;
    }

    public Order(long id, long memberId, OrderStatus status, int totalAmount, LocalDateTime createDate, LocalDateTime updateDate) {
        validateMemberId(memberId);
        validateTotalAmount(totalAmount);
        this.memberId = memberId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.id = id;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public void updateTotalAmount(int newAmount) {
        validateTotalAmount(newAmount);
        this.totalAmount = newAmount;
    }

    private void validateMemberId(long memberId) {
        if (memberId <= 0) {
            throw new RuntimeException("사용자 아이디가 잘못된 형식입니다.");
        }
    }

    private void validateTotalAmount(int totalAmount) {
        if (totalAmount < 0) {
            throw new RuntimeException("Total amount cannot be negative");
        }
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
