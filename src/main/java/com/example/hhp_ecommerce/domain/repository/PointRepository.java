package com.example.hhp_ecommerce.domain.repository;

import com.example.hhp_ecommerce.domain.Member;

public interface PointRepository {
    Member findByIdWithLock(long id);
    Member findById(long id);
    Member save(Member member);
}
