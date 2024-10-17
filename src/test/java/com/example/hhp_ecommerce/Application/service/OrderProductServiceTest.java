package com.example.hhp_ecommerce.Application.service;

import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.domain.OrderProduct;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.domain.repository.OrderProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)  // JUnit 5에서 Mockito 사용을 위한 확장
class OrderProductServiceTest {

    @Mock
    OrderProductRepository orderProductRepository;

    @InjectMocks
    OrderProductService orderProductService;

    @Test
    @DisplayName("주문 상품 목록에 주문 아이디 부여하기")
    public void orderProductSetOrderId() {
        // Given: Order와 Product 리스트를 생성
        Order order = new Order(1L, 1L, null, 0, null, null);

        Product product1 = new Product(100L, 2, 1000);

        Product product2 = new Product(101L, 3, 1500);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        // When: OrderProductService의 setOrderIdtoProductList 메서드를 호출
        List<OrderProduct> orderProductList = orderProductService.setOrderIdtoProductList(productList, order);

        // Then: 반환된 OrderProduct 리스트를 검증
        assertNotNull(orderProductList);  // 리스트가 null이 아닌지 확인
        assertEquals(2, orderProductList.size());  // 반환된 리스트의 크기가 예상대로 2인지 확인

        // 첫 번째 OrderProduct가 올바른 값들을 가지는지 검증
        OrderProduct orderProduct1 = orderProductList.get(0);
        assertEquals(1L, orderProduct1.getOrderId());  // Order ID가 1L인지 확인
        assertEquals(100L, orderProduct1.getProductId());  // Product ID가 100L인지 확인
        assertEquals(2, orderProduct1.getQuantity());  // 수량이 2인지 확인
        assertEquals(1000, orderProduct1.getPrice());  // 가격이 1000인지 확인

        // 두 번째 OrderProduct가 올바른 값들을 가지는지 검증
        OrderProduct orderProduct2 = orderProductList.get(1);
        assertEquals(1L, orderProduct2.getOrderId());  // Order ID가 1L인지 확인
        assertEquals(101L, orderProduct2.getProductId());  // Product ID가 101L인지 확인
        assertEquals(3, orderProduct2.getQuantity());  // 수량이 3인지 확인
        assertEquals(1500, orderProduct2.getPrice());  // 가격이 1500인지 확인
    }




}