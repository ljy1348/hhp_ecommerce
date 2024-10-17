package com.example.hhp_ecommerce.domain;

import lombok.Getter;

@Getter
public class Member {
    private long id;
    private String name;
    private int point;

    public Member(long id, int point) {
        validatePoint(point);
        this.id = id;
        this.point = point;
    }

    public Member(String name) {
        this.name = name;
    }

    public Member(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private void validatePoint(int point) {
        if (point < 0) {
            throw new RuntimeException("포인트는 음수가 될 수 없습니다.");
        }
    }

    public void updatePoint(int point) {
        int newPoint = this.point + point;
        validatePoint(newPoint);
        this.point = newPoint;
    }
}
