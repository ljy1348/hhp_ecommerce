package com.example.hhp_ecommerce.Application.facade;

import com.example.hhp_ecommerce.Application.mapper.ProductMapper;
import com.example.hhp_ecommerce.Application.service.ProductService;
import com.example.hhp_ecommerce.Application.usecase.ProductUseCase;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.domain.repository.ProductDetailRepository;
import com.example.hhp_ecommerce.domain.repository.ProductQuantityRepository;
import com.example.hhp_ecommerce.domain.repository.ProductRepository;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductDetailDto;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFacade implements ProductUseCase {
    private final ProductService productService;

    @Override
    public ProductDetailDto getProductDetail(long productId) {
        return ProductMapper.mapToProductDetailDto(productService.getProductDetail(productId));
    }

    public ProductListDto getProductList() {
        List<Product> products = productService.findAllProduct();
        List<Product> quantitys = productService.findAllQuantity();
        productService.mergedProductAndQuantity(products, quantitys);
        return ProductMapper.mapToProductListDto(products);
    }
}
