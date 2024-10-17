package com.example.hhp_ecommerce.domain.repository;

import com.example.hhp_ecommerce.domain.Product;

public interface ProductDetailRepository {
    Product getProductDetail(Long id);
}
