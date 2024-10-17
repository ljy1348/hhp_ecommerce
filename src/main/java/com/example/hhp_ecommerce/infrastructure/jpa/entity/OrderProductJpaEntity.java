package com.example.hhp_ecommerce.infrastructure.jpa.entity;

import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.domain.OrderProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_product")
public class OrderProductJpaEntity extends BaseTimeEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long orderId;
        private Long productId;
        private int quantity;
        private int price;

    OrderProductJpaEntity(long id, long orderId, long productId, int quantity, int price, LocalDateTime createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.createDate = createdAt;
    }

    public OrderProduct toDomain() {
        return new OrderProduct(
                this.id,
                this.orderId,
                this.productId
                ,this.quantity
                ,this.price
                ,this.createDate
        );
    }

    public static OrderProductJpaEntity toJpaEntity(OrderProduct orderProduct) {
        return new OrderProductJpaEntity(
                orderProduct.getId(),
                orderProduct.getOrderId(),
                orderProduct.getProductId(),
                orderProduct.getQuantity(),
                orderProduct.getPrice()
                ,orderProduct.getCreateDate()
        );
    }
}
