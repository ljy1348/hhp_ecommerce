package com.example.hhp_ecommerce.interfaces.dto.payment;

public record PaymentRequestDto(long orderId, long userId, long amount) {
}
