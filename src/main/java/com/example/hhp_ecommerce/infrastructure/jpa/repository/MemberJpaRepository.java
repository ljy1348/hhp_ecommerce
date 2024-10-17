package com.example.hhp_ecommerce.infrastructure.jpa.repository;

import com.example.hhp_ecommerce.infrastructure.jpa.entity.MemberJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberJpaEntity, Long> {
    MemberJpaEntity getById(long id);
}
