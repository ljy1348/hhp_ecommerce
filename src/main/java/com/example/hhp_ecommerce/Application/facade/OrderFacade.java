package com.example.hhp_ecommerce.Application.facade;

import com.example.hhp_ecommerce.Application.mapping.OrderMapping;
import com.example.hhp_ecommerce.Application.mapping.ProductMapping;
import com.example.hhp_ecommerce.Application.service.OrderProductService;
import com.example.hhp_ecommerce.Application.service.OrderService;
import com.example.hhp_ecommerce.Application.service.ProductService;
import com.example.hhp_ecommerce.Application.usecase.OrderUseCase;
import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.domain.OrderProduct;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderRequestDto;
import com.example.hhp_ecommerce.interfaces.dto.order.OrderResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacade implements OrderUseCase {
    private final OrderProductService orderProductService;
    private final OrderService orderService;
    private final ProductService productService;

    @Transactional
    @Override
    public OrderResponseDto order(OrderRequestDto orderRequestDto) {
        Order order = OrderMapping.orderRequestDtoToDomain(orderRequestDto);
        List<Product> orderProducts = ProductMapping.mapToProductList(orderRequestDto);
        List<Product> products = productService.findAllByProducts(orderProducts);
        List<Product> productQuantitys = productService.findAllProductQuantitys(orderProducts);
        List<Product> updateProducts = productService.updateOrderListWithProductPrices(products, orderProducts, productQuantitys);
        int totalAmount = productService.calculateTotalPrice(updateProducts);
        order.setTotalAmount(totalAmount);
        Order createOrder = orderService.createOrder(order);
        List<OrderProduct> orderProductList = orderProductService.setOrderIdtoProductList(updateProducts, createOrder);
        orderProductService.createOrderProductList(orderProductList);
        createOrder.setStatus(Order.OrderStatus.PAYMENT_PENDING);
        orderService.updateOrder(createOrder);
        return OrderMapping.domainToOrderResponseDto(createOrder);
    }

}
