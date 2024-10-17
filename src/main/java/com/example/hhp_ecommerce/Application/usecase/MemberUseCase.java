package com.example.hhp_ecommerce.Application.usecase;

import com.example.hhp_ecommerce.interfaces.dto.point.PointChargeDto;
import com.example.hhp_ecommerce.interfaces.dto.point.PointResponseDto;

public interface MemberUseCase {
    PointResponseDto pointLookup(long userId);
    PointResponseDto pointCharge(PointChargeDto pointChargeDto);
}
