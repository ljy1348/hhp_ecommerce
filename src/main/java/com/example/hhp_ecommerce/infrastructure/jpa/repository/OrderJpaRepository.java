package com.example.hhp_ecommerce.infrastructure.jpa.repository;

import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long> {
    OrderJpaEntity save(OrderJpaEntity order);
    OrderJpaEntity getById(long id);
}
