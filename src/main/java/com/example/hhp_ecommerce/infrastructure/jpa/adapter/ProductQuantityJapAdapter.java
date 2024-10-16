package com.example.hhp_ecommerce.infrastructure.jpa.adapter;

import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.domain.repository.ProductQuantityRepository;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.ProductJpaEntity;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.ProductQuantityJpaEntity;
import com.example.hhp_ecommerce.infrastructure.jpa.repository.ProductQuantityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductQuantityJapAdapter implements ProductQuantityRepository {

    private final ProductQuantityJpaRepository productQuantityJpaRepository;

    @Override
    public List<Product> findAllByProductId(List<Long> ids) {
        return productQuantityJpaRepository.findAllById(ids).stream()
                .map(ProductQuantityJpaEntity::toDomain)
                .collect(Collectors.toList());
    }

}
