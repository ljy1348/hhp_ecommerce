package com.example.hhp_ecommerce.Application.facade;



import com.example.hhp_ecommerce.Application.service.OrderProductService;
import com.example.hhp_ecommerce.Application.service.OrderService;
import com.example.hhp_ecommerce.Application.service.ProductService;
import com.example.hhp_ecommerce.domain.OrderProduct;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.infrastructure.jpa.repository.OrderJpaRepository;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductDetailDto;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductDto;
import com.example.hhp_ecommerce.interfaces.dto.product.ProductListDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductFacadeTest {

    @Autowired
    private ProductFacade productFacade;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @BeforeEach
    void setUpBeforeClass() throws Exception {
        orderJpaRepository.deleteAll();
    }


    @Test
    void testGetProductDetail() {
        ProductDetailDto productDetail = productFacade.getProductDetail(1L);

        assertNotNull(productDetail);
        assertEquals(1L, productDetail.productId());
        assertEquals("Product A", productDetail.productName());
        assertEquals(100, productDetail.amount());
        assertEquals(100, productDetail.quantity());
    }

    @Test
    void testGetProductList() {
        // 전체 상품 리스트 조회
        ProductListDto productList = productFacade.getProductList();

        assertNotNull(productList);
        assertEquals(3, productList.products().size());
        assertEquals("Product A", productList.products().get(0).productName());
        assertEquals("Product B", productList.products().get(1).productName());
        assertEquals("Product C", productList.products().get(2).productName());
    }
    @Test
    void testGetProductListTop5() {
        orderProductService.createOrderProductList(List.of(new OrderProduct(1L, 1L, 3, 400)
        , new OrderProduct(1L, 1L, 3, 400)
                ,new OrderProduct(1L, 1L, 3, 400)
                ,new OrderProduct(1L, 1L, 3, 400)
                ,new OrderProduct(1L, 2L, 3, 400)
                ,new OrderProduct(1L, 2L, 3, 400)
                ,new OrderProduct(1L, 2L, 4, 400)
        ));
        // 상위 5개 상품 조회
        ProductListDto top5Products = productFacade.getProductListTop5();

        assertNotNull(top5Products);
        List<ProductDto> products = top5Products.products();
        assertEquals(2, products.size());// 상품 1이 그다음으로 주문됨
    }

}