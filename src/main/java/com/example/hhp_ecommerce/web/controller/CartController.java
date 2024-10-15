package com.example.hhp_ecommerce.web.controller;

import com.example.hhp_ecommerce.web.dto.cart.CartListDto;
import com.example.hhp_ecommerce.web.dto.cart.CartProductDto;
import com.example.hhp_ecommerce.web.dto.product.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    // 장바구니 추가
    @PostMapping("/add/{userId}")
    public ResponseEntity<CartListDto> addCart(@PathVariable long userId, @RequestBody CartProductDto productDto) {
        ProductDto productDto1 = new ProductDto(1, 10, "장바구니 테스트1", 100);
        List<ProductDto> productDtos = new ArrayList<ProductDto>();
        productDtos.add(productDto1);
        CartListDto cartListDto = new CartListDto(productDtos);
        return new ResponseEntity<>(cartListDto, HttpStatus.OK);
    }

    // 장바구니 삭제
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<CartListDto> deleteCart(@PathVariable long userId, @RequestBody CartProductDto productDto) {
        ProductDto productDto1 = new ProductDto(1, 10, "장바구니 테스트1", 100);
        List<ProductDto> productDtos = new ArrayList<ProductDto>();
        productDtos.add(productDto1);
        CartListDto cartListDto = new CartListDto(productDtos);
        return new ResponseEntity<>(cartListDto, HttpStatus.OK);
    }

    // 장바구니 조회
    @GetMapping("/get/{userId}")
    public ResponseEntity<CartListDto> getCart(@PathVariable long userId) {
        ProductDto productDto1 = new ProductDto(1, 10, "장바구니 테스트1", 100);
        List<ProductDto> productDtos = new ArrayList<ProductDto>();
        productDtos.add(productDto1);
        CartListDto cartListDto = new CartListDto(productDtos);
        return new ResponseEntity<>(cartListDto, HttpStatus.OK);
    }

}
