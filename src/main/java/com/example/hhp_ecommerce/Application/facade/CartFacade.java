package com.example.hhp_ecommerce.Application.facade;

import com.example.hhp_ecommerce.Application.mapper.CartMapper;
import com.example.hhp_ecommerce.Application.service.CartService;
import com.example.hhp_ecommerce.Application.service.ProductService;
import com.example.hhp_ecommerce.Application.usecase.CartUseCase;
import com.example.hhp_ecommerce.domain.Cart;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.domain.repository.CartRepository;
import com.example.hhp_ecommerce.interfaces.dto.cart.CartListDto;
import com.example.hhp_ecommerce.interfaces.dto.cart.CartProductDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartFacade implements CartUseCase {

    private final CartService cartService;
    private final ProductService productService;

    @Transactional
    public CartListDto addCart(CartProductDto productDto) {
        Cart cart = CartMapper.CartProductDtoToDomain(productDto);
        Cart cart1 = cartService.addCart(cart);
        Product quantity = productService.getQuantity(cart1.getProductId());
        Product product = productService.getProduct(cart1.getProductId());
        quantity.quantityCheck(cart1.getQuantity());
        cartService.saveCart(cart1);
        product.updateQuantity(cart1.getQuantity());
        return CartMapper.domainToCartListDto(product);
    }

    @Transactional
    @Override
    public CartListDto getCartList(long memberId) {
        List<Cart> carts = cartService.getCartList(memberId);
        List<Long> ids = cartService.getProductIdList(carts);
        List<Product> products = productService.findAllByIds(ids);
        productService.mergedProductAndCart(products, carts);
        return CartMapper.domainToCartListDto(products);
    }

    @Transactional
    @Override
    public void deleteCart(CartProductDto dto) {
        Cart cart = CartMapper.CartProductDtoToDomain(dto);
        cartService.deleteOrRemove(cart);
    }

}
