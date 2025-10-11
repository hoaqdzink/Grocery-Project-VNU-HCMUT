package com.vinhnh.grocery.product.entity;

import com.vinhnh.grocery.common.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "product_image")
public class ProductImage extends AuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variant_id", nullable = false)
    private ProductVariant productVariant;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "alt_text")
    private String altText; // Mô tả hình ảnh cho accessibility

    @Column(name = "is_primary", nullable = false)
    private Boolean isPrimary = false;

    @Column(name = "display_order")
    private Integer displayOrder = 0; // Thứ tự hiển thị

    @Column(name = "image_type")
    private String imageType; // thumbnail, gallery, detail, etc.

    @Column(name = "file_size")
    private Long fileSize; // Kích thước file

    @Column(name = "width")
    private Integer width; // Chiều rộng ảnh

    @Column(name = "height")
    private Integer height; // Chiều cao ảnh
}
