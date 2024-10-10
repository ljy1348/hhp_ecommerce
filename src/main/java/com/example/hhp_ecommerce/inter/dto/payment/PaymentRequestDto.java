package com.example.hhp_ecommerce.inter.dto.payment;

public record PaymentRequestDto(long orderId, long userId, long amount) {
}
