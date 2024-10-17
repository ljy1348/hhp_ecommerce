package com.example.hhp_ecommerce.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Cart extends BaseTime{
    private long id;
    private long memberId;
    private long productId;
    private int quantity;

    public Cart(long memberId, long productId, int quantity) {
        this.memberId = memberId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Cart(long id, long memberId, long productid, int quantity, LocalDateTime createDate) {
        this.id = id;
        this.memberId = memberId;
        this.productId = productid;
        this.quantity = quantity;
        this.createDate = createDate;
    }

    public void updateQuantity(int quantity) {
        if (quantity < 0) throw new RuntimeException("장바구니 상품 수량에 문제가 발생했습니다.");
        this.quantity += quantity;
    }

    public void minusQuantity(int quantity) {
        if (quantity < 0) throw new RuntimeException("장바구니 상품 수량에 문제가 발생했습니다.");
        this.quantity -= quantity;
    }
}
