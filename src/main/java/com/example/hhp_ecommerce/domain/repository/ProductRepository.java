package com.example.hhp_ecommerce.domain.repository;

import com.example.hhp_ecommerce.domain.Product;

import java.util.List;

public interface ProductRepository {
    public List<Product> findAllByProducts(List<Long> products);
    public Product getById(Long id);
    List<Product> findAll();
}
