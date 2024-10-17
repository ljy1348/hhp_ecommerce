package com.example.hhp_ecommerce.infrastructure.jpa.adapter;

import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.domain.repository.ProductQuantityRepository;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.ProductJpaEntity;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.ProductQuantityJpaEntity;
import com.example.hhp_ecommerce.infrastructure.jpa.repository.ProductQuantityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductQuantityJapAdapter implements ProductQuantityRepository {

    private final ProductQuantityJpaRepository productQuantityJpaRepository;

    @Override
    public List<Product> findAllByProductIdWithLock(List<Long> ids) {
        return productQuantityJpaRepository.findAllByIdIn(ids).stream()
                .map(ProductQuantityJpaEntity::toDomain)
                .collect(Collectors.toList());
    }

    public List<Product> saveAll(List<Product> products) {
        List<ProductQuantityJpaEntity> jpaEntitys = new ArrayList<>();

        for (Product product : products) {
            jpaEntitys.add(ProductQuantityJpaEntity.toJpaEntity(product));
        }

        return productQuantityJpaRepository.saveAll(jpaEntitys).stream()
                .map(ProductQuantityJpaEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Product getById(Long id) {
        return productQuantityJpaRepository.getById(id).toDomain();
    }

    public List<Product> findAll() {
        return productQuantityJpaRepository.findAll().stream().map(ProductQuantityJpaEntity::toDomain).collect(Collectors.toList());
    }

    public List<Product> findAllById(List<Long> ids) {
        return productQuantityJpaRepository.findAllByIdIn(ids).stream().map(ProductQuantityJpaEntity::toDomain).collect(Collectors.toList());
    }

    public Product save(Product product) {
        return productQuantityJpaRepository.save(ProductQuantityJpaEntity.toJpaEntity(product)).toDomain();
    }
}
