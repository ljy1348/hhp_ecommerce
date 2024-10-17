package com.example.hhp_ecommerce.infrastructure.jpa.repository;

import com.example.hhp_ecommerce.infrastructure.jpa.entity.PaymentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentJpaEntity, Long> {
    PaymentJpaEntity save(PaymentJpaEntity paymentJpaEntity);
}
