package com.example.hhp_ecommerce.web.dto.payment;

public record PaymentRequestDto(long orderId, long userId, long amount) {
}
