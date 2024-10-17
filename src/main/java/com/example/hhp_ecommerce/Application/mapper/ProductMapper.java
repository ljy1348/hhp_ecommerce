package com.example.hhp_ecommerce.Application.mapper;

import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderProductDto;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderRequestDto;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductDetailDto;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductDto;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductListDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static List<Product> mapToProductList(OrderRequestDto orderRequestDto) {
        List<OrderProductDto> list = orderRequestDto.products();
        List<Product> products = new ArrayList<>();

        for (OrderProductDto orderProductDto : list) {
            Product product = new Product(orderProductDto.productId(), orderProductDto.quantity());
            products.add(product);
        }

        return products;
    }

    public static ProductDetailDto mapToProductDetailDto(Product product) {
        return new ProductDetailDto(product.getId(), product.getQuantity(), product.getName(), product.getPrice(), product.getDetail());
    }

    public static ProductListDto mapToProductListDto(List<Product> products) {
        List<ProductDto> productDtoList = products.stream()
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getQuantity(),
                        product.getName(),
                        product.getPrice()
                ))
                .collect(Collectors.toList());

        return new ProductListDto(productDtoList);
    }
}
