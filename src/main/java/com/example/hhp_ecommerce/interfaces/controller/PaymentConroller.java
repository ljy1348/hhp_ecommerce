package com.example.hhp_ecommerce.interfaces.controller;

import com.example.hhp_ecommerce.interfaces.dto.payment.PaymentRequestDto;
import com.example.hhp_ecommerce.interfaces.dto.payment.PaymentResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentConroller {

    @PostMapping("")
    public ResponseEntity<PaymentResponseDto> payment(@RequestBody PaymentRequestDto paymentRequestDto) {
        PaymentResponseDto responseDto = new PaymentResponseDto(1,"succ", paymentRequestDto.amount(), paymentRequestDto.userId(), paymentRequestDto.orderId());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
