package com.example.hhp_ecommerce.domain.repository;

import com.example.hhp_ecommerce.domain.OrderProduct;

import java.util.List;

public interface OrderProductRepository {
    List<OrderProduct> createOrderProductList(List<OrderProduct> orderProductList);
}
