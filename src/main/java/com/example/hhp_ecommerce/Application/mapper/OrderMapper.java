package com.example.hhp_ecommerce.Application.mapper;

import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderRequestDto;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderResponseDto;

public class OrderMapper {
    public static Order orderRequestDtoToDomain(OrderRequestDto orderRequestDto) {
        return new Order(orderRequestDto.userId());
    }

    public static OrderResponseDto domainToOrderResponseDto(Order order) {
        return new OrderResponseDto(order.getId(), order.getTotalAmount());
    }
}
