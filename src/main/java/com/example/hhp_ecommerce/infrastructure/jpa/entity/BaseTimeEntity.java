package com.example.hhp_ecommerce.infrastructure.jpa.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseTimeEntity {

    public LocalDateTime createDate;
    public LocalDateTime updateDate;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
        System.out.println("prePersist called: createDate = " + this.createDate);
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDateTime.now();
        System.out.println("preUpdate called: updateDate = " + this.updateDate);
    }

    // Getter
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
}