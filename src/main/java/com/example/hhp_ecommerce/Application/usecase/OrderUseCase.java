package com.example.hhp_ecommerce.Application.usecase;

import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderRequestDto;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderResponseDto;

public interface OrderUseCase {
    OrderResponseDto order(OrderRequestDto orderRequestDto);
}
