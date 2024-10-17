package com.example.hhp_ecommerce.Application.service;

import com.example.hhp_ecommerce.domain.Cart;
import com.example.hhp_ecommerce.domain.repository.CartRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    @DisplayName("장바구니에 동일 상품 있으면 수량 합산 테스트")
    public void testAddCartWhenCartExists() {
        // Given
        Cart existingCart = new Cart(1L, 1L, 5);  
        Cart newCart = new Cart(1L, 1L, 3);       

        when(cartRepository.getByMemberIdAndProductId(1L, 1L)).thenReturn(existingCart);

        // When
        Cart updatedCart = cartService.addCart(newCart);

        // Then
        assertEquals(8, updatedCart.getQuantity());  
    }

    @Test
    @DisplayName("장바구니에 동일 상품 없으면 상품 목록 생성 테스트")
    public void testAddCartWhenCartDoesNotExist() {
        // Given
        Cart newCart = new Cart(1L, 1L, 3);

        when(cartRepository.getByMemberIdAndProductId(1L, 1L)).thenReturn(null);

        // When
        Cart result = cartService.addCart(newCart);

        // Then
        assertEquals(newCart, result);  
    }

    @Test
    @DisplayName("장바구니에 상품 재고보다 적은 삭제 요청이면 재고 감소 테스트")
    public void testDeleteOrRemoveWhenCartExistsAndQuantityIsReduced() {
        // Given
        Cart existingCart = new Cart(1L, 1L, 5);  
        Cart cartToRemove = new Cart(1L, 1L, 3);  

        when(cartRepository.getByMemberIdAndProductId(1L, 1L)).thenReturn(existingCart);

        // When
        cartService.deleteOrRemove(cartToRemove);

        // Then
        assertEquals(2, existingCart.getQuantity()); 
    }

    @Test
    @DisplayName("장바구니에 상품 재고와 동일한 삭제 요청이면 목록 삭제 테스트")
    public void testDeleteOrRemoveWhenCartExistsAndQuantityIsZero() {
        // Given
        Cart existingCart = new Cart(1L, 1L, 3);  
        Cart cartToRemove = new Cart(1L, 1L, 3); 

        when(cartRepository.getByMemberIdAndProductId(1L, 1L)).thenReturn(existingCart);

        // When
        cartService.deleteOrRemove(cartToRemove);

        // Then
        verify(cartRepository).deleteByMemberIdAndProductId(1L, 1L);  
    }

    @Test
    @DisplayName("장바구니에 없는 상품 삭제 요청시 정상 동작 테스트")
    public void testDeleteOrRemoveWhenCartDoesNotExist() {
        // Given
        Cart cartToRemove = new Cart(1L, 1L, 3);  

        when(cartRepository.getByMemberIdAndProductId(1L, 1L)).thenReturn(null);

        // When
        cartService.deleteOrRemove(cartToRemove);

        // Then
        verify(cartRepository).deleteByMemberIdAndProductId(1L, 1L);  
    }

    @Test
    @DisplayName("장바구니 목록에서 상품 아이디 리스트 가져오기 테스트")
    public void testGetProductIdList() {
        // Given
        List<Cart> carts = Arrays.asList(
                new Cart(1L, 1L, 2),
                new Cart(1L, 2L, 3),
                new Cart(1L, 3L, 1)
        );

        // When
        List<Long> productIdList = cartService.getProductIdList(carts);

        // Then
        assertEquals(Arrays.asList(1L, 2L, 3L), productIdList);
    }

}