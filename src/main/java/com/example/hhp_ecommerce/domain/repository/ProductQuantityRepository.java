package com.example.hhp_ecommerce.domain.repository;

import com.example.hhp_ecommerce.domain.Product;

import java.util.List;

public interface ProductQuantityRepository {
    public List<Product> findAllByProductIdWithLock(List<Long> ids);
    public List<Product> saveAll(List<Product> products);
}
