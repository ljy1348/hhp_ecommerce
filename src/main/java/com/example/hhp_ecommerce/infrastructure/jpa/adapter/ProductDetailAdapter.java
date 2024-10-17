package com.example.hhp_ecommerce.infrastructure.jpa.adapter;

import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.domain.repository.ProductDetailRepository;
import com.example.hhp_ecommerce.domain.repository.ProductRepository;
import com.example.hhp_ecommerce.infrastructure.jpa.repository.ProductDetailJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductDetailAdapter implements ProductDetailRepository {
    private final ProductDetailJpaRepository productDetailJpaRepository;

    public Product getProductDetail(Long id) {
        return productDetailJpaRepository.getById(id).toDomain();
    }

}
