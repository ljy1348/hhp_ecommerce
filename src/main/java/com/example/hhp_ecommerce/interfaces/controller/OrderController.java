package com.example.hhp_ecommerce.interfaces.controller;

import com.example.hhp_ecommerce.Application.service.OrderService;
import com.example.hhp_ecommerce.Application.usecase.OrderUseCase;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderRequestDto;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderUseCase orderUseCase;

    @PostMapping("")
    public ResponseEntity<OrderResponseDto> addOrder(@RequestBody OrderRequestDto order) {
        OrderResponseDto orderResponseDto = orderUseCase.order(order);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

}
