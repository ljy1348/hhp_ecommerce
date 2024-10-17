package com.example.hhp_ecommerce.interfaces.controller;

import com.example.hhp_ecommerce.Application.usecase.PaymentUseCase;
import com.example.hhp_ecommerce.interfaces.dto.payment.PaymentRequestDto;
import com.example.hhp_ecommerce.interfaces.dto.payment.PaymentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentConroller {

    private final PaymentUseCase paymentUseCase;

    @PostMapping("")
    public PaymentResponseDto payment(@RequestBody PaymentRequestDto paymentRequestDto) {
        return paymentUseCase.payment(paymentRequestDto);
    }
}
