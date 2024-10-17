package com.example.hhp_ecommerce.interfaces.controller;

import com.example.hhp_ecommerce.Application.usecase.CartUseCase;
import com.example.hhp_ecommerce.interfaces.dto.cart.CartListDto;
import com.example.hhp_ecommerce.interfaces.dto.cart.CartProductDto;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartUseCase cartUseCase;

    @PostMapping("/add")
    public CartListDto addCart(@RequestBody CartProductDto productDto) {
        return cartUseCase.addCart(productDto);
    }

    // 장바구니 삭제
    @DeleteMapping("/delete")
    public void deleteCart(@RequestBody CartProductDto productDto) {
        cartUseCase.deleteCart(productDto);
    }

    // 장바구니 조회
    @GetMapping("/get/{memberId}")
    public CartListDto getCart(@PathVariable long memberId) {
        return cartUseCase.getCartList(memberId);
    }

}
