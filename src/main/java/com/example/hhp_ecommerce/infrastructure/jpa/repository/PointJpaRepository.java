package com.example.hhp_ecommerce.infrastructure.jpa.repository;


import com.example.hhp_ecommerce.domain.Member;
import com.example.hhp_ecommerce.infrastructure.jpa.entity.PointJpaEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface PointJpaRepository extends JpaRepository<PointJpaEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM PointJpaEntity p WHERE p.id = :id")
    PointJpaEntity getByIdWithLock(long id);

    // 락 없이 데이터를 조회
    PointJpaEntity getById(long id);

    PointJpaEntity save(PointJpaEntity point);
}
