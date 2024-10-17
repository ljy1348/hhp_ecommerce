package com.example.hhp_ecommerce.Application.mapper;

import com.example.hhp_ecommerce.domain.Member;
import com.example.hhp_ecommerce.interfaces.dto.point.PointChargeDto;
import com.example.hhp_ecommerce.interfaces.dto.point.PointResponseDto;

public class MemberMapper {
    public static PointResponseDto domainToPointResponseDto(Member member) {
        return new PointResponseDto(member.getPoint(), member.getId());
    }

    public static Member PointChargeDtoToDomain(PointChargeDto pointChargeDto) {
        return new Member(pointChargeDto.id(), pointChargeDto.chargePoint());
    }


}
