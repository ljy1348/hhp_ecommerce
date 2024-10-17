package com.example.hhp_ecommerce.Application.facade;

import com.example.hhp_ecommerce.Application.mapper.ProductMapper;
import com.example.hhp_ecommerce.Application.service.OrderProductService;
import com.example.hhp_ecommerce.Application.service.OrderService;
import com.example.hhp_ecommerce.Application.service.ProductService;
import com.example.hhp_ecommerce.Application.usecase.ProductUseCase;
import com.example.hhp_ecommerce.domain.Order;
import com.example.hhp_ecommerce.domain.OrderProduct;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.domain.repository.ProductDetailRepository;
import com.example.hhp_ecommerce.domain.repository.ProductQuantityRepository;
import com.example.hhp_ecommerce.domain.repository.ProductRepository;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductDetailDto;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductListDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFacade implements ProductUseCase {
    private final ProductService productService;
    private final OrderProductService orderProductService;

    @Transactional
    @Override
    public ProductDetailDto getProductDetail(long productId) {
        return ProductMapper.mapToProductDetailDto(productService.getProductDetail(productId));
    }

    @Transactional
    public ProductListDto getProductList() {
        List<Product> products = productService.findAllProduct();
        List<Product> quantitys = productService.findAllQuantity();
        productService.mergedProductAndQuantity(products, quantitys);
        return ProductMapper.mapToProductListDto(products);
    }

    @Transactional
    public ProductListDto getProductListTop5() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(3);
        List<Long> top5Ids = orderProductService.getProductIdTop5();
        List<Product> products = productService.findAllByIds(top5Ids);
        List<Product> quantitys = productService.findAllQuantityByIds(top5Ids);
        productService.mergedProductAndQuantity(products, quantitys);
        return ProductMapper.mapToProductListDto(products);
    }


}
