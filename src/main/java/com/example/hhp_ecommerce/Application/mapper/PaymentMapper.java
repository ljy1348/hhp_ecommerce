package com.example.hhp_ecommerce.Application.mapper;

import com.example.hhp_ecommerce.domain.Payment;
import com.example.hhp_ecommerce.interfaces.dto.payment.PaymentRequestDto;
import com.example.hhp_ecommerce.interfaces.dto.payment.PaymentResponseDto;

public class PaymentMapper {
    public static Payment PaymentRequestDtoToDomain(PaymentRequestDto dto) {
        return new Payment(dto.userId(), dto.orderId(), dto.amount());
    }

    public static PaymentResponseDto domainToResponseDto(Payment payment) {
        return new PaymentResponseDto(payment.getId(), payment.getAmount(), payment.getUserId(), payment.getOrderId());
    }
}
