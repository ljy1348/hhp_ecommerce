package com.example.hhp_ecommerce.inter.dto.cart;

import com.example.hhp_ecommerce.inter.dto.product.ProductDto;

import java.util.List;

public record CartListDto (List<ProductDto> products){
}
