package com.example.hhp_ecommerce.infrastructure.jpa.entity;

import com.example.hhp_ecommerce.domain.Member;
import com.example.hhp_ecommerce.domain.repository.PointRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "point")
public class PointJpaEntity  {

    @Id
    long id;
    int point;

    public Member toDomain() {
        return new Member(this.id, this.point);
    }

    public static PointJpaEntity toJpaEntity(Member member) {
        return new PointJpaEntity(member.getId(), member.getPoint());
    }
}
