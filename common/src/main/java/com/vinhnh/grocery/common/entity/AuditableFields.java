package com.vinhnh.grocery.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * AuditableFields - Mixin class chứa các audit fields không có ID
 * 
 * Chỉ chứa các field audit:
 * - createdAt: Thời gian tạo (tự động set)
 * - updatedAt: Thời gian cập nhật (tự động cập nhật)
 * - createdBy: Người tạo (tự động lấy từ Spring Security)
 * - updatedBy: Người cập nhật (tự động lấy từ Spring Security)
 * - isActive: Trạng thái hoạt động (default: true)
 * 
 * Sử dụng cho các entity có ID riêng hoặc composite key
 * Ví dụ: OrderDetail, UserRole, etc.
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableFields {

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
        if (isActive == null) {
            isActive = true;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
