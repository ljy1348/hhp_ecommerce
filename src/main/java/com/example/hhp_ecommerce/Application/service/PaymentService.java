package com.example.hhp_ecommerce.Application.service;

import com.example.hhp_ecommerce.domain.Payment;
import com.example.hhp_ecommerce.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}
