package com.vinhnh.grocery.product.service.impl;

import com.vinhnh.grocery.product.dto.CategoryCreateRequest;
import com.vinhnh.grocery.product.dto.CategoryResponse;
import com.vinhnh.grocery.product.dto.CategoryUpdateRequest;
import com.vinhnh.grocery.product.entity.Category;
import com.vinhnh.grocery.product.repository.CategoryRepository;
import com.vinhnh.grocery.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation của CategoryService
 * Cung cấp các phương thức CRUD và business logic cho Category
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryCreateRequest request) {
        log.info("Creating new category with name: {}", request.getName());
        
        // Kiểm tra category đã tồn tại chưa
        if (existsByName(request.getName())) {
            throw new IllegalArgumentException("Category với tên '" + request.getName() + "' đã tồn tại");
        }

        // Tạo category mới
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setDisplayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0);
        
        // Set parent nếu có
        if (request.getParentId() != null) {
            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent category không tồn tại"));
            category.setParent(parent);
        }

        Category savedCategory = categoryRepository.save(category);
        log.info("Created category with ID: {}", savedCategory.getId());
        
        return mapToResponse(savedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(UUID id) {
        log.info("Getting category by ID: {}", id);
        
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category không tồn tại với ID: " + id));
        
        return mapToResponse(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryResponse> getAllCategories(Pageable pageable) {
        log.info("Getting all categories with pagination");
        
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(this::mapToResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllActiveCategories() {
        log.info("Getting all active categories");
        
        List<Category> categories = categoryRepository.findAll().stream()
                .filter(Category::getIsActive)
                .collect(Collectors.toList());
        
        return categories.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryByName(String name) {
        log.info("Getting category by name: {}", name);
        
        Category category = categoryRepository.findAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name) && c.getIsActive())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category không tồn tại với tên: " + name));
        
        return mapToResponse(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> searchCategoriesByName(String name) {
        log.info("Searching categories by name: {}", name);
        
        List<Category> categories = categoryRepository.findAll().stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()) && c.getIsActive())
                .collect(Collectors.toList());
        
        return categories.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse updateCategory(UUID id, CategoryUpdateRequest request) {
        log.info("Updating category with ID: {}", id);
        
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category không tồn tại với ID: " + id));

        // Cập nhật thông tin
        if (request.getName() != null && !request.getName().trim().isEmpty()) {
            // Kiểm tra tên mới có trùng với category khác không
            if (!category.getName().equalsIgnoreCase(request.getName()) && 
                existsByName(request.getName())) {
                throw new IllegalArgumentException("Category với tên '" + request.getName() + "' đã tồn tại");
            }
            category.setName(request.getName());
        }
        
        if (request.getDescription() != null) {
            category.setDescription(request.getDescription());
        }
        
        if (request.getDisplayOrder() != null) {
            category.setDisplayOrder(request.getDisplayOrder());
        }
        
        if (request.getParentId() != null) {
            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent category không tồn tại"));
            category.setParent(parent);
        }

        Category updatedCategory = categoryRepository.save(category);
        log.info("Updated category with ID: {}", updatedCategory.getId());
        
        return mapToResponse(updatedCategory);
    }

    @Override
    public void deleteCategory(UUID id) {
        log.info("Deleting category with ID: {}", id);
        
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category không tồn tại với ID: " + id));
        
        // Soft delete
        category.setIsActive(false);
        categoryRepository.save(category);
        
        log.info("Deleted category with ID: {}", id);
    }

    @Override
    public CategoryResponse activateCategory(UUID id) {
        log.info("Activating category with ID: {}", id);
        
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category không tồn tại với ID: " + id));
        
        category.setIsActive(true);
        Category activatedCategory = categoryRepository.save(category);
        
        log.info("Activated category with ID: {}", id);
        return mapToResponse(activatedCategory);
    }

    @Override
    public CategoryResponse deactivateCategory(UUID id) {
        log.info("Deactivating category with ID: {}", id);
        
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category không tồn tại với ID: " + id));
        
        category.setIsActive(false);
        Category deactivatedCategory = categoryRepository.save(category);
        
        log.info("Deactivated category with ID: {}", id);
        return mapToResponse(deactivatedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(UUID id) {
        return categoryRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return categoryRepository.findAll().stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(name) && c.getIsActive());
    }

    @Override
    @Transactional(readOnly = true)
    public long countActiveCategories() {
        return categoryRepository.findAll().stream()
                .filter(Category::getIsActive)
                .count();
    }

    /**
     * Map Category entity sang CategoryResponse DTO
     */
    private CategoryResponse mapToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        response.setDisplayOrder(category.getDisplayOrder());
        response.setIsActive(category.getIsActive());
        response.setCreatedAt(category.getCreatedAt());
        response.setUpdatedAt(category.getUpdatedAt());
        response.setCreatedBy(category.getCreatedBy());
        response.setUpdatedBy(category.getUpdatedBy());
        
        // Set parent info nếu có
        if (category.getParent() != null) {
            response.setParentId(category.getParent().getId());
            response.setParentName(category.getParent().getName());
        }
        
        return response;
    }
}
