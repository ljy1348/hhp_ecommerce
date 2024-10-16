package com.example.hhp_ecommerce.infrastructure.jpa.repository;

import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {
    @Override
    List<ProductJpaEntity> findAllById(Iterable<Long> longs);
}
