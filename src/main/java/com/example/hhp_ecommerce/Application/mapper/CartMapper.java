package com.example.hhp_ecommerce.Application.mapper;

import com.example.hhp_ecommerce.domain.Cart;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.interfaces.dto.cart.CartListDto;
import com.example.hhp_ecommerce.interfaces.dto.cart.CartProductDto;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {
    public static Cart CartProductDtoToDomain(CartProductDto cartProductDto) {
        return new Cart(cartProductDto.memberId(), cartProductDto.productId(), cartProductDto.quantity());
    }

    public static CartListDto domainToCartListDto(List<Product> products) {
        List<ProductDto> list = products.stream().map(product -> new ProductDto(product.getId(), product.getQuantity(), product.getName(), product.getPrice()))
                .collect(Collectors.toUnmodifiableList());
        return new CartListDto(list);
    }

    public static CartListDto domainToCartListDto(Product product) {
        List<ProductDto> list = new ArrayList<>();
        list.add(new ProductDto(product.getId(), product.getQuantity(), product.getName(), product.getPrice()));
        return new CartListDto(list);
    }
}
