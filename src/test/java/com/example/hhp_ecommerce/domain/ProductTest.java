package com.example.hhp_ecommerce.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testUpdateQuantity_Success() {
        // Given: 올바른 초기 Product와 수량 업데이트
        Product product = new Product(1L, 10, 1000);  // id: 1, quantity: 10, price: 1000
        int newQuantity = 5;

        // When: updateQuantity 호출
        assertDoesNotThrow(() -> product.updateQuantity(newQuantity));

        // Then: 수량이 정상적으로 업데이트 되었는지 확인
        assertEquals(newQuantity, product.getQuantity());
    }

    @Test
    void testUpdateQuantity_Fail() {
        // Given: 초기 Product 생성 후 잘못된 수량으로 업데이트 시도
        Product product = new Product(1L, 10, 1000);  // id: 1, quantity: 10, price: 1000
        int invalidQuantity = -5;  // 잘못된 수량 (-5)

        // When & Then: updateQuantity 호출 시 예외 발생 확인
        assertThrows(IllegalArgumentException.class, () -> product.updateQuantity(invalidQuantity));
    }

}