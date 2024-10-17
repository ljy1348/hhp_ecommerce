package com.example.hhp_ecommerce.Application.usecase;

import com.example.hhp_ecommerce.interfaces.dto.product.ProductDetailDto;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductListDto;

public interface ProductUseCase {
    ProductDetailDto getProductDetail(long productId);

    ProductListDto getProductList();

    ProductListDto getProductListTop5();
}
