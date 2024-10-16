package com.example.hhp_ecommerce.infrastructure.jpa.entity;

import com.example.hhp_ecommerce.domain.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product_quantity")
@AllArgsConstructor
@NoArgsConstructor
public class ProductQuantityJpaEntity {

    @Id
    long id;
    int quantity;

    public Product toDomain() {
        return new Product(this.id, this.quantity);
    }

    public ProductQuantityJpaEntity toJpaEntity(Product product) {
        return new ProductQuantityJpaEntity(product.getId(), product.getQuantity());
    }

}
