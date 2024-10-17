package com.example.hhp_ecommerce.domain.repository;

import com.example.hhp_ecommerce.domain.Cart;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.CartJpaEntity;

import java.util.List;

public interface CartRepository {
    List<Cart> findAllByMemberId(Long memberId);
    Cart save(Cart cart);
    void deleteByMemberIdAndProductId(Long memberId, Long productId);
    Cart getByMemberIdAndProductId(Long memberId, Long productId);
}
