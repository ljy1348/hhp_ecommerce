package com.example.hhp_ecommerce.Application.facade;

import com.example.hhp_ecommerce.Application.service.OrderProductService;
import com.example.hhp_ecommerce.Application.service.OrderService;
import com.example.hhp_ecommerce.Application.service.ProductService;
import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderProductDto;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderRequestDto;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderResponseDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class OrderFacadeTest {

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProductService orderProductService;

    @BeforeEach
    void setUpBeforeClass() throws Exception {
        productService.saveQuantity(new Product(1L, 100));
        productService.saveQuantity(new Product(2L, 200));
        productService.saveProduct(new Product(1L, "Product A", 5000));
        productService.saveProduct(new Product(2L, "Product B", 10000));
    }

//    @BeforeEach
//    void setUp() {
//        // 테스트를 위한 기본적인 데이터 셋업
//        // 기본적인 상품 추가, 재고 초기화 등
//        Product product1 = new Product(1L, "Product A", 5000, 100); // 상품 1: 재고 100, 가격 5000
//        Product product2 = new Product(2L, "Product B", 10000, 200); // 상품 2: 재고 200, 가격 10000
//        productService.saveProduct(product1);
//        productService.saveProduct(product2);
//    }

    @Test
    void testOrder() {
        // 주문 요청 생성
        OrderRequestDto orderRequestDto = new OrderRequestDto(List.of(
                new OrderProductDto(1L, 2), // 상품 1: 수량 2
                new OrderProductDto(2L, 1)  // 상품 2: 수량 1
        ), 1L);

        // 주문 처리
        OrderResponseDto response = orderFacade.order(orderRequestDto);

        // 주문 응답 검증
        assertNotNull(response);
        assertEquals(20000, response.amount()); // 총 금액 = 2 * 5000 + 1 * 10000 = 20000
    }

    @Test
    void testOrderWithInsufficientStock() {
        // 주문 요청 생성 (재고 부족)
        OrderRequestDto orderRequestDto = new OrderRequestDto(List.of(
                new OrderProductDto(1L, 150) // 상품 1: 수량 150 (재고 부족)
        ), 1L);

        // 재고 부족으로 인한 예외 발생 예상
        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            orderFacade.order(orderRequestDto);
        });
    }

    @Test
    void testOrderTotalPriceCalculation() {
        // 상품 2개를 포함하는 주문 요청 생성
        OrderRequestDto orderRequestDto = new OrderRequestDto(List.of(
                new OrderProductDto(1L, 3), // 상품 1: 수량 3
                new OrderProductDto(2L, 2)  // 상품 2: 수량 2
        ), 1L);

        // 주문 처리
        OrderResponseDto response = orderFacade.order(orderRequestDto);

        // 총 금액 계산 검증 (3 * 5000 + 2 * 10000 = 35000)
        assertEquals(35000, response.amount());
    }

    @Transactional
    @Test
    void testConcurrentOrders() throws InterruptedException, ExecutionException {
        int numberOfThreads = 30;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        List<Callable<OrderResponseDto>> tasks = new ArrayList<>();

        for (long i = 0; i < numberOfThreads; i++) {
            tasks.add(() -> {
                long id = 0;
                id++;
                try {
                    latch.countDown();
                    latch.await(); // 모든 스레드가 준비될 때까지 대기
                    OrderRequestDto orderRequestDto = new OrderRequestDto(List.of(
                            new OrderProductDto(3L, 1)
                    ), id);
                    OrderResponseDto dto = orderFacade.order(orderRequestDto);
                    System.out.println(dto.orderId());
                    return dto;
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            });
        }

        executorService.invokeAll(tasks);
        Product product = productService.getQuantity(3L);
        assertEquals(70, product.getQuantity());

    }

}