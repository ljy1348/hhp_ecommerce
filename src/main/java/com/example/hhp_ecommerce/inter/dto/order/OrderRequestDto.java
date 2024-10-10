package com.example.hhp_ecommerce.inter.dto.order;

import com.example.hhp_ecommerce.inter.dto.product.ProductListDto;

import java.util.List;

public record OrderRequestDto(List<OrderProductDto> products, long userId) {
}
