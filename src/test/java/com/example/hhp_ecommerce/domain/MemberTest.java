package com.example.hhp_ecommerce.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("멤버 point 검증 정상 테스트")
    void testValidatePoint_Success() {
        // Given: 올바른 point로 Member 생성
        int validPoint = 100;

        // When & Then: 예외가 발생하지 않고 객체가 정상적으로 생성되는지 확인
        assertDoesNotThrow(() -> new Member(1L, validPoint));
    }

    @Test
    @DisplayName("멤버 point 수정 정상 테스트")
    void testUpdatePoint_Success() {
        // Given: 초기 Member 생성 후 포인트 업데이트
        Member member = new Member(1L, 50);
        int pointToAdd = 20;

        // When: 포인트를 추가
        member.updatePoint(pointToAdd);

        // Then: 포인트가 정상적으로 업데이트 되었는지 확인
        assertEquals(70, member.getPoint());
    }

    @Test
    @DisplayName("멤버 point 검증 실패 테스트")
    void testValidatePoint_Fail() {
        // Given: 음수 point로 Member 생성 시도
        int invalidPoint = -10;

        // When & Then: RuntimeException 발생 여부 확인
        assertThrows(RuntimeException.class, () -> new Member(1L, invalidPoint));
    }

    @Test
    @DisplayName("멤버 point 수정 실패 테스트")
    void testUpdatePoint_Fail() {
        // Given: 초기 Member 생성 후 음수 포인트를 더해 포인트 업데이트 시도
        Member member = new Member(1L, 50);
        int pointToAdd = -100;  // 더한 후 포인트가 음수가 됨

        // When & Then: RuntimeException 발생 여부 확인
        assertThrows(RuntimeException.class, () -> member.updatePoint(pointToAdd));
    }

}