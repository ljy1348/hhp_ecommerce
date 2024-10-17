package com.example.hhp_ecommerce.Application.service;

import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.domain.OrderProduct;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.domain.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Long> getProductIdTop5() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(3);
        List<OrderProduct> orderProductList = orderProductRepository.getOrderProductCreateDateBetween(startDate, endDate);
        Map<Long, Long> productCountMap = orderProductList.stream()
                .collect(Collectors.groupingBy(
                        OrderProduct::getProductId,  // productId로 그룹화
                        Collectors.counting()        // 각 productId의 갯수 세기
                ));

        // 갯수 기준으로 정렬하고 상위 5개의 productId만 가져오기
        return productCountMap.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed()) // 갯수 기준 내림차순 정렬
                .limit(5)  // 상위 5개만 가져오기
                .map(Map.Entry::getKey)  // productId만 가져오기
                .collect(Collectors.toList());
    }

}
