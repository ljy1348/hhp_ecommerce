package com.example.hhp_ecommerce.interfaces.dto.cart;

import com.example.hhp_ecommerce.interfaces.dto.product.ProductDto;

import java.util.List;

public record CartListDto (List<ProductDto> products){
}
