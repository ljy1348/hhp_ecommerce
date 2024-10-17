package com.example.hhp_ecommerce.domain.repository;

import com.example.hhp_ecommerce.domain.Payment;

public interface PaymentRepository {
    Payment save(Payment payment);
}
