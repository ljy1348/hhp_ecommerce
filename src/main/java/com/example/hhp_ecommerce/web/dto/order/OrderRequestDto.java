package com.example.hhp_ecommerce.web.dto.order;

import java.util.List;

public record OrderRequestDto(List<OrderProductDto> products, long userId) {
}
