package com.example.hhp_ecommerce.Application.service;

import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.domain.repository.ProductQuantityRepository;
import com.example.hhp_ecommerce.domain.repository.ProductRepository;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderProductDto;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductQuantityRepository productQuantityRepository;

    public List<Product> findAllByProducts(List<Product> products) {
        List<Long> productIds = products.stream()
                .map(Product::getId) // productId 추출
                .collect(Collectors.toList());
        return productRepository.findAllByProducts(productIds);
    }

    public List<Product> findAllProductQuantitys(List<Product> products) {
        List<Long> productIds = products.stream()
                .map(Product::getId) // productId 추출
                .collect(Collectors.toList());
        return productQuantityRepository.findAllByProductId(productIds);
    }

    public List<Product> saveAllProductQuantity(List<Product> products) {
        return productQuantityRepository.saveAll(products);
    }

    public void isOrderQuantityAvailableAndUpdate(List<Product> products, List<Product> orderProducts) {
        for (Product orderProduct : orderProducts) {
            Product product = products.stream()
                    .filter(p -> p.getId() == (orderProduct.getId()))  // equals() 대신 == 사용 (long 타입일 때는 == 사용 가능)
                    .findFirst()
                    .orElse(null);

            // 제품 수량을 차감
            product.updateQuantity(product.getQuantity() - orderProduct.getQuantity());
        }
    }

    public List<Product> updateOrderListWithProductPrices(List<Product> products, List<Product> orderProducts) {
        List<Product> updatedOrderList = new ArrayList<>();

        for (Product orderProduct : orderProducts) {
            Product product = products.stream()
                    .filter(p -> p.getId() == orderProduct.getId())  // long 타입이므로 == 사용
                    .findFirst()
                    .orElse(null);

            if (product == null) {
                throw new IllegalArgumentException("상품을 찾을 수 없습니다: " + orderProduct.getId());
            }

            // 가격을 products에서 가져와서 orderProduct에 반영
            Product updatedOrderProduct = new Product(orderProduct.getId(), orderProduct.getQuantity(), product.getPrice());
            updatedOrderList.add(updatedOrderProduct);
        }

        return updatedOrderList;
    }

    public int calculateTotalPrice(List<Product> products) {
        int totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }

}
