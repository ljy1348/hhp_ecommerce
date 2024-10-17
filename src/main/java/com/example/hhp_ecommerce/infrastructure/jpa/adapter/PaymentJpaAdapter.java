package com.example.hhp_ecommerce.infrastructure.jpa.adapter;

import com.example.hhp_ecommerce.domain.Payment;
import com.example.hhp_ecommerce.domain.repository.PaymentRepository;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.PaymentJpaEntity;
import com.example.hhp_ecommerce.infrastructure.jpa.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentJpaAdapter implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentJpaRepository.save(PaymentJpaEntity.toJpaEntity(payment)).toDomain();
    }
}
