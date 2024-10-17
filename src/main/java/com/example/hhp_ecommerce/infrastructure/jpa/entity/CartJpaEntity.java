package com.example.hhp_ecommerce.infrastructure.jpa.entity;

import com.example.hhp_ecommerce.domain.Cart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartJpaEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long memberId;
    private long productId;
    private int quantity;

    public CartJpaEntity(Cart cart) {
        this.id = cart.getId();
        this.memberId = cart.getMemberId();
        this.productId = cart.getProductId();
        this.quantity = cart.getQuantity();
        this.createDate = cart.getCreateDate();
    }

    public Cart toDomain() {
        return new Cart(this.id, this.memberId, this.productId, this.quantity, this.createDate);
    }

    public static CartJpaEntity toJpaEntity(Cart cart) {
        return new CartJpaEntity(cart);
    }
}
