package com.example.hhp_ecommerce.Application.usecase;

import com.example.hhp_ecommerce.interfaces.dto.payment.PaymentRequestDto;
import com.example.hhp_ecommerce.interfaces.dto.payment.PaymentResponseDto;

public interface PaymentUseCase {
    public PaymentResponseDto payment(PaymentRequestDto dto);
}
