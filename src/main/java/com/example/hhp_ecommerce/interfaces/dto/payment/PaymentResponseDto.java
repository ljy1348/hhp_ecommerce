package com.example.hhp_ecommerce.interfaces.dto.payment;

public record PaymentResponseDto(long paymentId, long amount, long userId, long orderId) {
}
