package com.example.hhp_ecommerce.Application.service;

import com.example.hhp_ecommerce.domain.Cart;
import com.example.hhp_ecommerce.domain.Product;
import com.example.hhp_ecommerce.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<Cart> getCartList(long memberId) {
        return cartRepository.findAllByMemberId(memberId);
    }

    public Cart getCartByProductId(long memberId, long productId) {
        return cartRepository.getByMemberIdAndProductId(memberId, productId);
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart addCart(Cart cart) {
        try {
            Cart cart1 = getCartByProductId(cart.getMemberId(), cart.getProductId());
            cart1.updateQuantity(cart.getQuantity());
            return cart1;
        } catch (NullPointerException e) {
            return cart;
        }


    }

    public void deleteOrRemove(Cart cart) {
        try {
            Cart cart1 = getCartByProductId(cart.getMemberId(), cart.getProductId());
            if (cart1.getQuantity() > cart.getQuantity()) {
                cart1.minusQuantity(cart.getQuantity());
                saveCart(cart1);
            } else {
                cartRepository.deleteByMemberIdAndProductId(cart.getMemberId(), cart.getProductId());
            }
        } catch (NullPointerException e) {
            cartRepository.deleteByMemberIdAndProductId(cart.getMemberId(), cart.getProductId());
        }
    }

    public List<Long> getProductIdList(List<Cart> carts) {
        return carts.stream()
                .map(Cart::getProductId)  // Cart 객체에서 getProductId() 호출
                .collect(Collectors.toList());
    }
}
