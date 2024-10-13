package com.example.hhp_ecommerce.web.dto.cart;

import com.example.hhp_ecommerce.web.dto.product.ProductDto;

import java.util.List;

public record CartListDto (List<ProductDto> products){
}
