package com.example.hhp_ecommerce.inter.dto.payment;

public record PaymentResponseDto(long paymentId, String result, long amount, long userId, long orderId) {
}
