package com.example.hhp_ecommerce.interfaces.controller;

import com.example.hhp_ecommerce.Application.service.MemberService;
import com.example.hhp_ecommerce.Application.usecase.MemberUseCase;
import com.example.hhp_ecommerce.interfaces.dto.point.PointChargeDto;
import com.example.hhp_ecommerce.interfaces.dto.point.PointResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
public class MemberController {

    private final MemberUseCase memberUseCase;

    @GetMapping("/get/{userId}")
    public PointResponseDto getPoint(@PathVariable long userId) {
        return memberUseCase.pointLookup(userId);
    }

    @PatchMapping("/charge")
    public PointResponseDto pointCharge(@RequestBody PointChargeDto pointChargeDto) {
        return memberUseCase.pointCharge(pointChargeDto);
    }
}
