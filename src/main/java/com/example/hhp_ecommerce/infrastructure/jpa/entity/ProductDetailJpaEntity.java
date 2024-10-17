package com.example.hhp_ecommerce.infrastructure.jpa.entity;

import com.example.hhp_ecommerce.domain.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailJpaEntity {
    @Id
    long id;
    String detail;

    public Product toDomain() {
        return new Product(this.id, this.detail);
    }

    public static ProductDetailJpaEntity toJpaEntity(Product product) {
        return new ProductDetailJpaEntity(product.getId(), product.getDetail());
    }
}
