package com.example.hhp_ecommerce.Application.facade;

import com.example.hhp_ecommerce.Application.mapper.PaymentMapper;
import com.example.hhp_ecommerce.Application.service.MemberService;
import com.example.hhp_ecommerce.Application.service.OrderService;
import com.example.hhp_ecommerce.Application.service.PaymentService;
import com.example.hhp_ecommerce.Application.usecase.PaymentUseCase;
import com.example.hhp_ecommerce.domain.Member;
import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.domain.Payment;
import com.example.hhp_ecommerce.interfaces.dto.payment.PaymentRequestDto;
import com.example.hhp_ecommerce.interfaces.dto.payment.PaymentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentFacade implements PaymentUseCase {
    private final OrderService orderService;
    private final MemberService memberService;
    private final PaymentService paymentService;

    public PaymentResponseDto payment(PaymentRequestDto paymentRequestDto) {
        Payment payment = PaymentMapper.PaymentRequestDtoToDomain(paymentRequestDto);
        Order order = orderService.findById(payment.getOrderId());
        Member member = memberService.pointLookup(order.getMemberId());
        memberService.pointUse(member, order.getTotalAmount());
        order.updateStatus(Order.OrderStatus.PAYMENT_COMPLETED);
        Payment newPayment = new Payment(payment.getUserId(), payment.getOrderId(), order.getTotalAmount());
        orderService.updateOrder(order);
        memberService.pointSave(member);
        Payment payment1 = paymentService.save(newPayment);
        return PaymentMapper.domainToResponseDto(payment1);
    }
}
