package com.example.hhp_ecommerce.infrastructure.jpa.repository;

import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.domain.OrderProduct;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.OrderProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderProductJpaRepository extends JpaRepository<OrderProductJpaEntity, Long> {
    List<OrderProductJpaEntity> findAllByCreateDateBetween(LocalDateTime start, LocalDateTime end);
}
