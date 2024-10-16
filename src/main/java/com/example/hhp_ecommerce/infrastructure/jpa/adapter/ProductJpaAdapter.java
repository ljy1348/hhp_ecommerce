package com.example.hhp_ecommerce.infrastructure.jpa.adapter;

import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.domain.repository.OrderRepository;
import com.example.hhp_ecommerce.domain.repository.ProductRepository;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.ProductJpaEntity;
import com.example.hhp_ecommerce.infrastructure.jpa.repository.OrderJpaRepository;
import com.example.hhp_ecommerce.infrastructure.jpa.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;

    @Override
    public List<Product> findAllByProducts(List<Long> ids) {
        // JPA 리포지토리를 통해 id로 상품 리스트 조회
        List<ProductJpaEntity> productJpaEntities = productJpaRepository.findAllById(ids);

        // 조회된 JPA 엔티티 리스트를 도메인 객체 리스트로 변환하여 반환
        return productJpaEntities.stream()
                .map(ProductJpaEntity::toDomain)
                .collect(Collectors.toList());
    }
}
