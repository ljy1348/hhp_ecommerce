package com.example.hhp_ecommerce.infrastructure.jpa.adapter;

import com.example.hhp_ecommerce.domain.Member;
import com.example.hhp_ecommerce.domain.repository.PointRepository;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.PointJpaEntity;
import com.example.hhp_ecommerce.infrastructure.jpa.repository.PointJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointJpaAdapter implements PointRepository {

    private final PointJpaRepository pointJpaRepository;

    @Override
    public Member findByIdWithLock(long id) {
        return pointJpaRepository.getByIdWithLock(id).toDomain();
    }

    public Member findById(long id) {
        return pointJpaRepository.getById(id).toDomain();
    }

    public Member save(Member member) {
        return pointJpaRepository.save(PointJpaEntity.toJpaEntity(member)).toDomain();
    }
}
