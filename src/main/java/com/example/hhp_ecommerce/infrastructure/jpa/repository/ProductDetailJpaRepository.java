package com.example.hhp_ecommerce.infrastructure.jpa.repository;

import com.example.hhp_ecommerce.infrastructure.jpa.entity.ProductDetailJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailJpaRepository extends JpaRepository<ProductDetailJpaEntity, Long> {
    ProductDetailJpaEntity getById(Long id);
}
