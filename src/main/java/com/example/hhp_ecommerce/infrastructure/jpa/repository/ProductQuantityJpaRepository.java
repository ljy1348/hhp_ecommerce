package com.example.hhp_ecommerce.infrastructure.jpa.repository;

import com.example.hhp_ecommerce.infrastructure.jpa.entity.ProductJpaEntity;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.ProductQuantityJpaEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface ProductQuantityJpaRepository extends JpaRepository<ProductQuantityJpaEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<ProductQuantityJpaEntity> findAllByIdIn(List<Long> id);
}
