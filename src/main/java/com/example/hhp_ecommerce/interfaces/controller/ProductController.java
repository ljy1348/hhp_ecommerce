package com.example.hhp_ecommerce.interfaces.controller;

import com.example.hhp_ecommerce.Application.usecase.ProductUseCase;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductDetailDto;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductDto;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;

    @GetMapping("/list")
    public ProductListDto getProductDetail( ) {
        return productUseCase.getProductList();
    }

    //    상세 조회
    @GetMapping("/detail/{productId}")
    public ProductDetailDto getProductDetail(@PathVariable long productId) {
        return productUseCase.getProductDetail(productId);
    }

//    상위 상품 조회
@GetMapping("/list/popular")
public ResponseEntity<ProductListDto> getProductPopular() {
    ProductDto mock1 = new ProductDto(1, 10, "인기상품1", 100);
    ProductDto mock2 = new ProductDto(1, 15, "인기상품2", 200);

    List<ProductDto> products = new ArrayList<>();
    products.add(mock1);
    products.add(mock2);
    ProductListDto productListDto = new ProductListDto(products);

    return new ResponseEntity<>(productListDto, HttpStatus.OK);
}
}
