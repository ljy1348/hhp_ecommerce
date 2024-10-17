package com.example.hhp_ecommerce.infrastructure.jpa.adapter;

import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.domain.OrderProduct;
import com.example.hhp_ecommerce.domain.repository.OrderProductRepository;
import com.example.hhp_ecommerce.domain.repository.OrderRepository;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.OrderProductJpaEntity;
import com.example.hhp_ecommerce.infrastructure.jpa.repository.OrderJpaRepository;
import com.example.hhp_ecommerce.infrastructure.jpa.repository.OrderProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderProductJpaAdapter implements OrderProductRepository {
    private final OrderProductJpaRepository orderProductJpaRepository;

    @Override
    public List<OrderProduct> createOrderProductList(List<OrderProduct> orderProductList) {
        // 도메인 객체 리스트를 JPA 엔티티 리스트로 변환
        List<OrderProductJpaEntity> orderProductJpaEntities = orderProductList.stream()
                .map(OrderProductJpaEntity::toJpaEntity)
                .collect(Collectors.toList());

        // JPA 리포지토리를 통해 엔티티 리스트 저장
        List<OrderProductJpaEntity> savedOrderProductEntities = orderProductJpaRepository.saveAll(orderProductJpaEntities);

        // 저장된 JPA 엔티티 리스트를 도메인 객체 리스트로 변환
        return savedOrderProductEntities.stream()
                .map(OrderProductJpaEntity::toDomain)
                .collect(Collectors.toList());
    }

    public List<OrderProduct> getOrderProductCreateDateBetween(LocalDateTime start, LocalDateTime end) {
        return orderProductJpaRepository.findAllByCreateDateBetween(start, end).stream().map(orderProduct -> orderProduct.toDomain()).collect(Collectors.toList());
    }
}
