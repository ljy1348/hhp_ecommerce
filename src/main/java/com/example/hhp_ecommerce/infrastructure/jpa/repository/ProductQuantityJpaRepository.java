package com.example.hhp_ecommerce.infrastructure.jpa.repository;

import com.example.hhp_ecommerce.infrastructure.jpa.entity.ProductJpaEntity;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.ProductQuantityJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductQuantityJpaRepository extends JpaRepository<ProductQuantityJpaEntity, Long> {

}
