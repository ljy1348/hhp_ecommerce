package com.example.hhp_ecommerce.domain.repository;

import com.example.hhp_ecommerce.domain.Member;

public interface MemberRepository {
    public Member findById(long id);
}
