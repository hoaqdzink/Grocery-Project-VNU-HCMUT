package com.vinhnh.grocery.product.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO cho request cập nhật Category
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateRequest {

    @Size(min = 2, max = 100, message = "Tên category phải từ 2-100 ký tự")
    private String name;

    @Size(max = 500, message = "Mô tả không được quá 500 ký tự")
    private String description;

    private UUID parentId;

    private Integer displayOrder;
}
