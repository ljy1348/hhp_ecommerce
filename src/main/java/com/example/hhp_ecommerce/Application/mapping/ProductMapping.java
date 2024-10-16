package com.example.hhp_ecommerce.Application.mapping;

import com.example.hhp_ecommerce.domain.OrderProduct;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderProductDto;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderRequestDto;

import java.util.ArrayList;
import java.util.List;

public class ProductMapping {

    public static List<Product> mapToProductList(OrderRequestDto orderRequestDto) {
        List<OrderProductDto> list = orderRequestDto.products();
        List<Product> products = new ArrayList<>();

        for (OrderProductDto orderProductDto : list) {
            Product product = new Product(orderProductDto.productId(), orderProductDto.quantity());
            products.add(product);
        }

        return products;
    }
}
