package com.vinhnh.grocery.product.entity;

import com.vinhnh.grocery.common.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "product_variant")
public class ProductVariant extends AuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "size")
    private String size;

    @Column(name = "color")
    private String color;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "sku", unique = true, nullable = false)
    private String sku; // Stock Keeping Unit - mã sản phẩm duy nhất

    @Column(name = "weight", precision = 8, scale = 3)
    private BigDecimal weight; // Trọng lượng sản phẩm

    @Column(name = "dimensions")
    private String dimensions; // Kích thước (L x W x H)
}
