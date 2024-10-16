package com.example.hhp_ecommerce.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseTime {
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
