package com.example.hhp_ecommerce.infrastructure.jpa.entity;

import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.domain.OrderProduct;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductJpaEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long orderId;
        private Long productId;
        private int quantity;
        private int price;

    public OrderProduct toDomain() {
        return new OrderProduct(
                this.id,
                this.orderId,
                this.productId
                ,this.quantity
                ,this.price
        );
    }

    public static OrderProductJpaEntity toJpaEntity(OrderProduct orderProduct) {
        return new OrderProductJpaEntity(
                orderProduct.getId(),
                orderProduct.getOrderId(),
                orderProduct.getProductId(),
                orderProduct.getQuantity(),
                orderProduct.getPrice()
        );
    }
}
