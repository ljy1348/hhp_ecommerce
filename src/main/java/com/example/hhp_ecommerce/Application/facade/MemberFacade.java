package com.example.hhp_ecommerce.Application.facade;

import com.example.hhp_ecommerce.Application.mapper.MemberMapper;
import com.example.hhp_ecommerce.Application.service.MemberService;
import com.example.hhp_ecommerce.Application.usecase.MemberUseCase;
import com.example.hhp_ecommerce.domain.Member;
import com.example.hhp_ecommerce.domain.repository.MemberRepository;
import com.example.hhp_ecommerce.interfaces.dto.point.PointChargeDto;
import com.example.hhp_ecommerce.interfaces.dto.point.PointResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberFacade implements MemberUseCase {
    private final MemberService memberService;

    @Override
    public PointResponseDto pointLookup(long userId) {
        return MemberMapper.domainToPointResponseDto(memberService.pointLookup(userId));
    }

    @Override
    public PointResponseDto pointCharge(PointChargeDto pointChargeDto) {
        Member charge = MemberMapper.PointChargeDtoToDomain(pointChargeDto);
        Member member = memberService.pointLookup(charge.getId());
        member.updatePoint(charge.getPoint());
        return MemberMapper.domainToPointResponseDto(memberService.pointSave(member));
    }
}
