package com.example.hhp_ecommerce.Application.service;

import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.domain.repository.ProductQuantityRepository;
import com.example.hhp_ecommerce.domain.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @Mock
    ProductQuantityRepository productQuantityRepository;

    @InjectMocks
    ProductService productService;

    @Test
    @DisplayName("주문할 상품 목록으로 실제 상품 정보 목록 가져오기")
    void testFindAllByProducts() {
        // Given: 테스트를 위한 Product 리스트 생성
        Product product1 = new Product(1L, 2);
        Product product2 = new Product(2L, 3);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        // ProductRepository의 모의 동작 설정
        List<Product> mockResult = new ArrayList<>();
        mockResult.add(product1);
        mockResult.add(product2);

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);

        // findAllByProducts 메서드가 호출될 때 productIds를 파라미터로 받으면 mockResult 반환
        when(productRepository.findAllByProducts(ids)).thenReturn(mockResult);

        // When: ProductService의 findAllByProducts 호출
        List<Product> result = productService.findAllByProducts(productList);

        // Then: 결과 검증
        assertEquals(2, result.size());  // 반환된 리스트의 크기 검증
        assertEquals(product1.getId(), result.get(0).getId());  // 첫 번째 제품의 ID 검증
        assertEquals(product2.getId(), result.get(1).getId());  // 두 번째 제품의 ID 검증
    }

    @Test
    @DisplayName("상품 재고 주문수 만큼 차감하기 성공 테스트")
    void testIsOrderQuantityAvailableAndUpdate() {
        // Given: 재고 리스트와 주문 상품 리스트를 생성
        Product product1 = new Product(1L, 10, 1000);  // 재고 수량 10
        Product product2 = new Product(2L, 20, 1500);  // 재고 수량 20

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Product orderProduct1 = new Product(1L, 10, 1000);  // 주문 수량 2
        Product orderProduct2 = new Product(2L, 5, 1500);  // 주문 수량 5

        List<Product> orderProducts = new ArrayList<>();
        orderProducts.add(orderProduct1);
        orderProducts.add(orderProduct2);

        // When: ProductService의 isOrderQuantityAvailableAndUpdate 메서드 호출
        productService.isOrderQuantityAvailableAndUpdate(products, orderProducts);

        // Then: 재고 수량이 올바르게 차감되었는지 확인
        assertEquals(0, products.get(0).getQuantity());  // product1의 재고는 10에서 2가 차감되어 8이 되어야 함
        assertEquals(15, products.get(1).getQuantity());
    }

    @Test
    @DisplayName("상품 재고 보다 많은 수량 주문 하면 에러 발생 테스트")
    void testIsOrderQuantityAvailableAndUpdate_ThrowsExceptionWhenStockIsInsufficient() {
        // Given: 재고 리스트와 주문 상품 리스트 생성
        Product product1 = new Product(1L, 5, 1000);  // 재고 수량 5

        List<Product> products = new ArrayList<>();
        products.add(product1);

        Product orderProduct1 = new Product(1L, 10, 1000);  // 주문 수량 10 (재고보다 많음)

        List<Product> orderProducts = new ArrayList<>();
        orderProducts.add(orderProduct1);

        assertThrows(IllegalArgumentException.class, () -> {
            productService.isOrderQuantityAvailableAndUpdate(products, orderProducts);
        });
    }

    @Test
    @DisplayName("주문 총액 계산 테스트")
    void testCalculateTotalPrice() {
        // Given: Product 리스트를 생성하고 가격과 수량을 설정
        Product product1 = new Product(1L, 2, 1000);  // 수량 2, 가격 1000
        Product product2 = new Product(2L, 3, 1500);  // 수량 3, 가격 1500

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        // 예상 총 가격: (2 * 1000) + (3 * 1500) = 2000 + 4500 = 6500

        // When: calculateTotalPrice 메서드 호출
        int totalPrice = productService.calculateTotalPrice(products);

        // Then: 예상 총 가격과 실제 계산된 총 가격 비교
        assertEquals(6500, totalPrice);  // 예상 총 가격과 계산된 총 가격이 같은지 확인
    }
}