package com.example.hhp_ecommerce.Application.usecase;

import com.example.hhp_ecommerce.interfaces.dto.cart.CartListDto;
import com.example.hhp_ecommerce.interfaces.dto.cart.CartProductDto;

public interface CartUseCase {
    // 추가
    CartListDto addCart(CartProductDto dto);
    // 조회
    CartListDto getCartList(long memberId);
    // 삭제
    void deleteCart(CartProductDto dto);
}
