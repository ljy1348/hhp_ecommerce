package com.example.hhp_ecommerce.Application.facade;

import com.example.hhp_ecommerce.Application.service.CartService;
import com.example.hhp_ecommerce.Application.service.ProductService;
import com.example.hhp_ecommerce.domain.Cart;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.interfaces.dto.cart.CartListDto;
import com.example.hhp_ecommerce.interfaces.dto.cart.CartProductDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartFacadeTest {

    @Autowired
    private CartFacade cartFacade;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @BeforeEach
    void setUp() {
        CartProductDto dto = new CartProductDto(1L, 1L, 10);
        cartFacade.deleteCart(dto);
        Cart cart = new Cart(1L, 1L, 1); // memberId 1, productId 1, 수량 10
        cartService.saveCart(cart);
    }

    @Test
    void testAddCart() {
        CartProductDto cartProductDto = new CartProductDto(1L, 1L, 5); // memberId 1, productId 1, 수량 5
        CartListDto result = cartFacade.addCart(cartProductDto);

        assertNotNull(result);
        assertEquals(1, result.products().size());
        assertEquals(6, result.products().get(0).quantity());
    }

    @Test
    void testAddCartOverQuantity() {
        CartProductDto cartProductDto = new CartProductDto(1L, 1L, 150); // memberId 1, productId 1, 수량 5
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cartFacade.addCart(cartProductDto);
        });
    }

    @Test
    void testGetCartList() {
        CartListDto cartListDto = cartFacade.getCartList(1L); // memberId 1에 대한 장바구니 조회

        assertNotNull(cartListDto);
        assertEquals(1, cartListDto.products().size());
    }

    @Test
    void testDeleteCart() {
        CartProductDto cartProductDto = new CartProductDto(1L, 1L, 10); // memberId 1, productId 1, 수량 10
        cartFacade.deleteCart(cartProductDto);

        List<Cart> carts = cartService.getCartList(1L);
        assertEquals(0, carts.size()); // 장바구니가 삭제되었는지 확인
    }

}