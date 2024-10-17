package com.example.hhp_ecommerce.Application.service;

import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.domain.OrderProduct;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.domain.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public List<OrderProduct> createOrderProductList(List<OrderProduct> orderProductList) {
        return orderProductRepository.createOrderProductList(orderProductList);
    }

    public List<OrderProduct> setOrderIdtoProductList(List<Product> productList, Order order) {
        List<OrderProduct> orderProductList = new ArrayList<>();
        for (Product orderProduct : productList) {
            OrderProduct orderProduct1 = new OrderProduct(order.getId(), orderProduct.getId(), orderProduct.getQuantity(), orderProduct.getPrice());
            orderProductList.add(orderProduct1);
        }
        return orderProductList;
    }

}
