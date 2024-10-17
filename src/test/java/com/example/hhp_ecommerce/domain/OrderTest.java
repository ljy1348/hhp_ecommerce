package com.example.hhp_ecommerce.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderTest {

    @Test
    @DisplayName("회원 아이디 검증 로직 성공 테스트")
    void testValidateMemberId_Success() {
        // Given: 올바른 memberId
        long validMemberId = 1L;

        // When & Then: 올바른 memberId로 예외가 발생하지 않는지 검증
        assertDoesNotThrow(() -> new Order(validMemberId));
    }

    @Test
    @DisplayName("주문 총액 검증 로직 성공 테스트")
    void testValidateTotalAmount_Success() {
        // Given: 올바른 memberId, status, totalAmount
        long validMemberId = 1L;
        int validTotalAmount = 1000;

        // When & Then: 올바른 totalAmount로 예외가 발생하지 않는지 검증
        assertDoesNotThrow(() -> new Order(1L, validMemberId, Order.OrderStatus.ORDER_PENDING, validTotalAmount));
    }

    @Test
    @DisplayName("회원 아이디 검증 로직 실패 테스트")
    void testValidateMemberId_Fail() {
        // Given: 잘못된 memberId
        long invalidMemberId = -1L;

        // When & Then: 잘못된 memberId로 RuntimeException 발생하는지 검증
        assertThrows(RuntimeException.class, () -> new Order(invalidMemberId));
    }

    @Test
    @DisplayName("주문 총액 검증 로직 실패 테스트")
    void testValidateTotalAmount_Fail() {
        // Given: 잘못된 totalAmount
        long validMemberId = 1L;
        int invalidTotalAmount = -1000;

        // When & Then: 잘못된 totalAmount로 IllegalArgumentException 발생하는지 검증
        assertThrows(RuntimeException.class, () -> new Order(1L, validMemberId, Order.OrderStatus.ORDER_PENDING, invalidTotalAmount));
    }


}