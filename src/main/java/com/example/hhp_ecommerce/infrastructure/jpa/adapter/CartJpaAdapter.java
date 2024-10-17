package com.example.hhp_ecommerce.infrastructure.jpa.adapter;

import com.example.hhp_ecommerce.domain.Cart;
import com.example.hhp_ecommerce.domain.repository.CartRepository;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.CartJpaEntity;
import com.example.hhp_ecommerce.infrastructure.jpa.repository.CartJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CartJpaAdapter implements CartRepository {
    private final CartJpaRepository cartJpaRepository;

    @Override
    public List<Cart> findAllByMemberId(Long memberId) {
        return cartJpaRepository.findAllByMemberId(memberId).stream().map(entity -> entity.toDomain()).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Cart save(Cart cart) {
        return cartJpaRepository.save(CartJpaEntity.toJpaEntity(cart)).toDomain();
    }

    @Override
    public void deleteByMemberIdAndProductId(Long memberId, Long productId) {
        cartJpaRepository.deleteByMemberIdAndProductId(memberId, productId);
    }

    @Override
    public Cart getByMemberIdAndProductId(Long memberId, Long productId) {
        return cartJpaRepository.getByMemberIdAndProductId(memberId, productId).toDomain();
    }
}
