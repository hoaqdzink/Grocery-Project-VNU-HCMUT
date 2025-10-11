package com.vinhnh.grocery.product.service;

import com.vinhnh.grocery.product.dto.CategoryCreateRequest;
import com.vinhnh.grocery.product.dto.CategoryResponse;
import com.vinhnh.grocery.product.dto.CategoryUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * Service interface cho Category
 * Cung cấp các phương thức CRUD và business logic cho Category
 */
public interface CategoryService {

    /**
     * Tạo category mới
     * @param request thông tin tạo category
     * @return CategoryResponse
     */
    CategoryResponse createCategory(CategoryCreateRequest request);

    /**
     * Lấy category theo ID
     * @param id UUID của category
     * @return CategoryResponse
     */
    CategoryResponse getCategoryById(UUID id);

    /**
     * Lấy tất cả category với phân trang
     * @param pageable thông tin phân trang
     * @return Page<CategoryResponse>
     */
    Page<CategoryResponse> getAllCategories(Pageable pageable);

    /**
     * Lấy tất cả category đang hoạt động
     * @return List<CategoryResponse>
     */
    List<CategoryResponse> getAllActiveCategories();

    /**
     * Tìm category theo tên
     * @param name tên category
     * @return CategoryResponse
     */
    CategoryResponse getCategoryByName(String name);

    /**
     * Tìm category theo tên chứa từ khóa
     * @param name từ khóa tìm kiếm
     * @return List<CategoryResponse>
     */
    List<CategoryResponse> searchCategoriesByName(String name);

    /**
     * Cập nhật category
     * @param id UUID của category
     * @param request thông tin cập nhật
     * @return CategoryResponse
     */
    CategoryResponse updateCategory(UUID id, CategoryUpdateRequest request);

    /**
     * Xóa category (soft delete)
     * @param id UUID của category
     */
    void deleteCategory(UUID id);

    /**
     * Kích hoạt category
     * @param id UUID của category
     * @return CategoryResponse
     */
    CategoryResponse activateCategory(UUID id);

    /**
     * Vô hiệu hóa category
     * @param id UUID của category
     * @return CategoryResponse
     */
    CategoryResponse deactivateCategory(UUID id);

    /**
     * Kiểm tra category có tồn tại không
     * @param id UUID của category
     * @return boolean
     */
    boolean existsById(UUID id);

    /**
     * Kiểm tra category có tồn tại theo tên không
     * @param name tên category
     * @return boolean
     */
    boolean existsByName(String name);

    /**
     * Đếm số category đang hoạt động
     * @return long
     */
    long countActiveCategories();
}
