package com.example.hhp_ecommerce.web.controller;

import com.example.hhp_ecommerce.web.dto.point.PointChargeDto;
import com.example.hhp_ecommerce.web.dto.point.PointResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/point")
public class PointController {

    @GetMapping("/get/{userId}")
    public ResponseEntity<PointResponseDto> getPoint(@PathVariable long userId) {
        PointResponseDto mockPoint = new PointResponseDto(1000, userId);

        return new ResponseEntity<>(mockPoint, HttpStatus.OK);
    }

    @PatchMapping("/charge/{userId}")
    public ResponseEntity<PointResponseDto> chargePoint(@PathVariable long userId
                                                , @RequestBody PointChargeDto pointChargeDto) {
        PointResponseDto mockPoint = new PointResponseDto(userId, 1000+ pointChargeDto.chargePoint());

        return new ResponseEntity<>(mockPoint, HttpStatus.OK);
    }
}
