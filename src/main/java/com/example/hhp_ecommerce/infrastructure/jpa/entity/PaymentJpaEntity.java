package com.example.hhp_ecommerce.infrastructure.jpa.entity;

import com.example.hhp_ecommerce.Application.service.PaymentService;
import com.example.hhp_ecommerce.domain.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long orderId;
    private long userId;
    private int amount;

    public Payment toDomain() {
        return new Payment(this.id, this.userId, this.orderId
        , this.amount);
    }

    public static PaymentJpaEntity toJpaEntity(Payment payment) {
        return new PaymentJpaEntity(payment.getId(), payment.getOrderId(), payment.getUserId()
        , payment.getAmount());
    }
}
