package com.example.hhp_ecommerce.inter.controller;

import com.example.hhp_ecommerce.inter.dto.order.OrderRequestDto;
import com.example.hhp_ecommerce.inter.dto.order.OrderResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @PostMapping("")
    public ResponseEntity<OrderResponseDto> addOrder(@RequestBody OrderRequestDto order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto(1, 10000);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

}
