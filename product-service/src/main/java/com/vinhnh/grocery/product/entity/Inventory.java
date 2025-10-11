package com.vinhnh.grocery.product.entity;

import com.vinhnh.grocery.common.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "inventory")
public class Inventory extends AuditableEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variant_id", unique = true)
    private ProductVariant productVariant;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "reserved_quantity", nullable = false)
    private Integer reservedQuantity = 0; // Số lượng đã được đặt hàng

    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity; // Số lượng có thể bán

    @Column(name = "min_stock_level")
    private Integer minStockLevel; // Mức tồn kho tối thiểu

    @Column(name = "max_stock_level")
    private Integer maxStockLevel; // Mức tồn kho tối đa

    @Column(name = "last_restock_date")
    private LocalDateTime lastRestockDate;

    @PreUpdate
    @PrePersist
    public void updateAvailableQuantity() {
        this.availableQuantity = this.quantity - this.reservedQuantity;
        // updatedAt will be automatically set by AuditingEntityListener
    }
}
