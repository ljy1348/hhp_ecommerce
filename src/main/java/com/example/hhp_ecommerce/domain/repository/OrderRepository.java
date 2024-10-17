package com.example.hhp_ecommerce.domain.repository;

import com.example.hhp_ecommerce.domain.Order;

public interface OrderRepository {
    public Order save(Order order);
    public Order getById(long id);
}
