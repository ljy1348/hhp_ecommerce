package com.example.hhp_ecommerce.Application.service;

import com.example.hhp_ecommerce.domain.Member;
import com.example.hhp_ecommerce.domain.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PointRepository pointRepository;

    public Member pointLookupWithLock(long memberId) {
        return pointRepository.findByIdWithLock(memberId);
    }

    public Member pointLookup(long memberId) {
        return pointRepository.findById(memberId);
    }

    public Member pointSave(Member member) {
        return pointRepository.save(member);
    }

    public Member pointUse(Member member, int usePoint) {
        member.updatePoint(-usePoint);
        return member;
    }
}
