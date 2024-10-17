package com.example.hhp_ecommerce.Application.facade;

import com.example.hhp_ecommerce.Application.service.MemberService;
import com.example.hhp_ecommerce.Application.service.OrderService;
import com.example.hhp_ecommerce.Application.service.PaymentService;
import com.example.hhp_ecommerce.domain.Member;
import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.interfaces.dto.payment.PaymentRequestDto;
import com.example.hhp_ecommerce.interfaces.dto.payment.PaymentResponseDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class PaymentFacadeTest {

    @Autowired
    private PaymentFacade paymentFacade;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PaymentService paymentService;

    @BeforeEach
    public void setup() {
        Member member = new Member(1L,10000);
        memberService.pointSave(member);
    }

    @Transactional
    @Test
    void testPayment() {
        Order order = new Order(2L, 1L, Order.OrderStatus.ORDER_PENDING, 5000);
        orderService.createOrder(order);
        orderService.createOrder(order);
        // 결제 요청 생성
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(2L, 1L, 0);

        // 결제 처리
        PaymentResponseDto response = paymentFacade.payment(paymentRequestDto);

        // 결제 응답 검증
        assertNotNull(response);
        assertEquals(5000, response.amount()); // 결제 금액 검증

        // 주문 상태가 결제 완료로 변경되었는지 확인
        Order updatedOrder = orderService.findById(2L);
        assertEquals(Order.OrderStatus.PAYMENT_COMPLETED, updatedOrder.getStatus());

        // 유저 포인트 차감 확인 (초기 10,000 - 5,000 = 5,000)
        Member updatedMember = memberService.pointLookupWithLock(1L);
        assertEquals(5000, updatedMember.getPoint());
    }

    @Test
    void testPaymentWithInsufficientPoints() {
        Order order = new Order(1L, 1L, Order.OrderStatus.ORDER_PENDING, 99999);
        orderService.createOrder(order);

        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(1L, 1L, 0);

        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            paymentFacade.payment(paymentRequestDto);
        });
    }

    @Test
    void testPaymentForInvalidOrder() {
        // 잘못된 주문 ID로 결제 시도
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(999L, 1L, 0);

        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            paymentFacade.payment(paymentRequestDto);
        });
    }

}