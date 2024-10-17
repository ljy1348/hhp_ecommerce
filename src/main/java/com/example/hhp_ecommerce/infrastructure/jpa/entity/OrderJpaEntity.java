package com.example.hhp_ecommerce.infrastructure.jpa.entity;

import com.example.hhp_ecommerce.domain.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class OrderJpaEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private int totalAmount;
    private Order.OrderStatus status;

    OrderJpaEntity(long id, long memberId, int totalAmount, Order.OrderStatus status, LocalDateTime createDate) {
        this.id = id;
        this.memberId = memberId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createDate = createDate;
    }

    public Order toDomain() {
        return new Order(
                this.id,
                this.memberId,
                this.status
        ,this.totalAmount
                ,this.createDate
                ,this.updateDate
        );
    }

    public static OrderJpaEntity toJpaEntity(Order order) {
        return new OrderJpaEntity(
                order.getId(),
                order.getMemberId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreateDate()
        );
    }

}
