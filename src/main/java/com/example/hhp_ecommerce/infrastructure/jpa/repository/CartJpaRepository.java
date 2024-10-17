package com.example.hhp_ecommerce.infrastructure.jpa.repository;

import com.example.hhp_ecommerce.infrastructure.jpa.entity.CartJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartJpaRepository extends JpaRepository<CartJpaEntity, Long> {
    List<CartJpaEntity> findAllByMemberId(Long memberId);
    CartJpaEntity save(CartJpaEntity cart);
    void deleteByMemberIdAndProductId(Long memberId, Long productId);
    CartJpaEntity getByMemberIdAndProductId(Long memberId, Long productId);
}
