package com.example.hhp_ecommerce.infrastructure.jpa.entity;

import com.example.hhp_ecommerce.domain.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductJpaEntity extends BaseTimeEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private int price;
        private String name;

        // JPA 엔티티에서 도메인 객체로 변환하는 메서드
        public Product toDomain() {
                return new Product(
                        this.id,
                        this.name,
                        this.price
                );
        }

        // 도메인 객체에서 JPA 엔티티로 변환하는 메서드
        public static ProductJpaEntity toJpaEntity(Product product) {
                return new ProductJpaEntity(
                        product.getId(),
                        product.getPrice(),
                        product.getName()
                );
        }
}
