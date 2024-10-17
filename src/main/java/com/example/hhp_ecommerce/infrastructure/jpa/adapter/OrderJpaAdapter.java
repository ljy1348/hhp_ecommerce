package com.example.hhp_ecommerce.infrastructure.jpa.adapter;

import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.domain.repository.OrderRepository;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.OrderJpaEntity;
import com.example.hhp_ecommerce.infrastructure.jpa.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderJpaAdapter implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;

    public Order save(Order order) {
        OrderJpaEntity orderJpaEntity = OrderJpaEntity.toJpaEntity(order);
        return orderJpaRepository.save(orderJpaEntity).toDomain();
    }

    @Override
    public Order getById(long id) {
        return orderJpaRepository.getById(id).toDomain();
    }
}
