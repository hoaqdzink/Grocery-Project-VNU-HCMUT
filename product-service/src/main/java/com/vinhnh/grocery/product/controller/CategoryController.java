package com.vinhnh.grocery.product.controller;

import com.vinhnh.grocery.common.dto.DataResponse;
import com.vinhnh.grocery.product.dto.CategoryCreateRequest;
import com.vinhnh.grocery.product.dto.CategoryResponse;
import com.vinhnh.grocery.product.dto.CategoryUpdateRequest;
import com.vinhnh.grocery.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST Controller cho Category
 * Cung cấp các API endpoints để quản lý categories
 */
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@Tag(name = "Category Management", description = "APIs để quản lý categories")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Tạo category mới
     * POST /api/v1/categories
     */
    @PostMapping
    @Operation(
        summary = "Tạo category mới", 
        description = "Tạo một category mới trong hệ thống với tên và mô tả được cung cấp. " +
                     "Category sẽ được tạo với trạng thái active mặc định.",
        tags = {"Category Management"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Category được tạo thành công",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DataResponse.class),
                examples = @ExampleObject(
                    name = "Success Response",
                    value = """
                    {
                        "success": true,
                        "message": "Category được tạo thành công",
                        "data": {
                            "id": "123e4567-e89b-12d3-a456-426614174000",
                            "name": "Rau củ quả",
                            "description": "Các loại rau củ quả tươi",
                            "isActive": true,
                            "createdAt": "2024-01-15T10:30:00Z",
                            "updatedAt": "2024-01-15T10:30:00Z"
                        }
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Dữ liệu đầu vào không hợp lệ",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Validation Error",
                    value = """
                    {
                        "success": false,
                        "message": "Tên category không được để trống",
                        "data": null
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "409", 
            description = "Category với tên này đã tồn tại",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Conflict Error",
                    value = """
                    {
                        "success": false,
                        "message": "Category với tên này đã tồn tại",
                        "data": null
                    }
                    """
                )
            )
        )
    })
    public ResponseEntity<DataResponse<CategoryResponse>> createCategory(
        @Parameter(
            description = "Thông tin category cần tạo",
            required = true,
            schema = @Schema(implementation = CategoryCreateRequest.class)
        )
        @Valid @RequestBody CategoryCreateRequest request) {
        log.info("Creating new category: {}", request.getName());
        CategoryResponse response = categoryService.createCategory(request);
        return ResponseEntity.ok(DataResponse.created("Category được tạo thành công", response));
    }

    /**
     * Lấy category theo ID
     * GET /api/v1/categories/{id}
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "Lấy category theo ID", 
        description = "Lấy thông tin chi tiết của một category theo ID. " +
                     "Trả về thông tin đầy đủ bao gồm tên, mô tả, trạng thái và thời gian tạo/cập nhật.",
        tags = {"Category Management"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Lấy thông tin category thành công",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DataResponse.class),
                examples = @ExampleObject(
                    name = "Success Response",
                    value = """
                    {
                        "success": true,
                        "message": "Lấy category thành công",
                        "data": {
                            "id": "123e4567-e89b-12d3-a456-426614174000",
                            "name": "Rau củ quả",
                            "description": "Các loại rau củ quả tươi",
                            "isActive": true,
                            "createdAt": "2024-01-15T10:30:00Z",
                            "updatedAt": "2024-01-15T10:30:00Z"
                        }
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Không tìm thấy category với ID này",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Not Found Error",
                    value = """
                    {
                        "success": false,
                        "message": "Không tìm thấy category với ID: 123e4567-e89b-12d3-a456-426614174000",
                        "data": null
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "ID không hợp lệ",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Invalid ID Error",
                    value = """
                    {
                        "success": false,
                        "message": "ID không hợp lệ",
                        "data": null
                    }
                    """
                )
            )
        )
    })
    public ResponseEntity<DataResponse<CategoryResponse>> getCategoryById(
            @Parameter(
                description = "UUID của category cần lấy thông tin",
                required = true,
                example = "123e4567-e89b-12d3-a456-426614174000"
            ) 
            @PathVariable UUID id) {
        log.info("Getting category by ID: {}", id);
        CategoryResponse response = categoryService.getCategoryById(id);
        return ResponseEntity.ok(DataResponse.success("Lấy category thành công", response));
    }

    /**
     * Lấy tất cả categories với phân trang
     * GET /api/v1/categories?page=0&size=10&sort=name,asc
     */
    @GetMapping
    @Operation(
        summary = "Lấy danh sách categories có phân trang", 
        description = "Lấy danh sách tất cả categories với hỗ trợ phân trang, sắp xếp và lọc. " +
                     "Mặc định trả về 20 items mỗi trang, sắp xếp theo tên.",
        tags = {"Category Management"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Lấy danh sách categories thành công",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DataResponse.class),
                examples = @ExampleObject(
                    name = "Success Response",
                    value = """
                    {
                        "success": true,
                        "message": "Lấy danh sách categories thành công",
                        "data": {
                            "content": [
                                {
                                    "id": "123e4567-e89b-12d3-a456-426614174000",
                                    "name": "Rau củ quả",
                                    "description": "Các loại rau củ quả tươi",
                                    "isActive": true,
                                    "createdAt": "2024-01-15T10:30:00Z",
                                    "updatedAt": "2024-01-15T10:30:00Z"
                                }
                            ],
                            "pageable": {
                                "pageNumber": 0,
                                "pageSize": 20,
                                "sort": {
                                    "sorted": true,
                                    "unsorted": false
                                }
                            },
                            "totalElements": 1,
                            "totalPages": 1,
                            "first": true,
                            "last": true,
                            "numberOfElements": 1
                        }
                    }
                    """
                )
            )
        )
    })
    public ResponseEntity<DataResponse<Page<CategoryResponse>>> getAllCategories(
            @Parameter(
                description = "Thông tin phân trang và sắp xếp",
                example = "page=0&size=20&sort=name,asc"
            )
            @PageableDefault(size = 20, sort = "name") Pageable pageable) {
        log.info("Getting all categories with pagination: {}", pageable);
        Page<CategoryResponse> response = categoryService.getAllCategories(pageable);
        return ResponseEntity.ok(DataResponse.success("Lấy danh sách categories thành công", response));
    }

    /**
     * Lấy tất cả categories đang hoạt động
     * GET /api/v1/categories/active
     */
    @GetMapping("/active")
    @Operation(
        summary = "Lấy danh sách categories đang hoạt động", 
        description = "Lấy danh sách tất cả categories có trạng thái active (isActive = true). " +
                     "Không hỗ trợ phân trang, trả về toàn bộ danh sách.",
        tags = {"Category Management"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Lấy danh sách categories đang hoạt động thành công",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = List.class),
                examples = @ExampleObject(
                    name = "Success Response",
                    value = """
                    [
                        {
                            "id": "123e4567-e89b-12d3-a456-426614174000",
                            "name": "Rau củ quả",
                            "description": "Các loại rau củ quả tươi",
                            "isActive": true,
                            "createdAt": "2024-01-15T10:30:00Z",
                            "updatedAt": "2024-01-15T10:30:00Z"
                        },
                        {
                            "id": "456e7890-e89b-12d3-a456-426614174001",
                            "name": "Thịt cá",
                            "description": "Các loại thịt cá tươi",
                            "isActive": true,
                            "createdAt": "2024-01-15T11:00:00Z",
                            "updatedAt": "2024-01-15T11:00:00Z"
                        }
                    ]
                    """
                )
            )
        )
    })
    public ResponseEntity<List<CategoryResponse>> getAllActiveCategories() {
        log.info("Getting all active categories");
        List<CategoryResponse> response = categoryService.getAllActiveCategories();
        return ResponseEntity.ok(response);
    }

    /**
     * Tìm category theo tên
     * GET /api/v1/categories/name/{name}
     */
    @GetMapping("/name/{name}")
    @Operation(
        summary = "Tìm category theo tên chính xác", 
        description = "Tìm kiếm category với tên chính xác (case-sensitive). " +
                     "Trả về thông tin category đầu tiên tìm thấy.",
        tags = {"Category Management"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Tìm thấy category",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CategoryResponse.class),
                examples = @ExampleObject(
                    name = "Success Response",
                    value = """
                    {
                        "id": "123e4567-e89b-12d3-a456-426614174000",
                        "name": "Rau củ quả",
                        "description": "Các loại rau củ quả tươi",
                        "isActive": true,
                        "createdAt": "2024-01-15T10:30:00Z",
                        "updatedAt": "2024-01-15T10:30:00Z"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Không tìm thấy category với tên này",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Not Found Error",
                    value = """
                    {
                        "success": false,
                        "message": "Không tìm thấy category với tên: Rau củ quả",
                        "data": null
                    }
                    """
                )
            )
        )
    })
    public ResponseEntity<CategoryResponse> getCategoryByName(
            @Parameter(
                description = "Tên chính xác của category cần tìm",
                required = true,
                example = "Rau củ quả"
            )
            @PathVariable String name) {
        log.info("Getting category by name: {}", name);
        CategoryResponse response = categoryService.getCategoryByName(name);
        return ResponseEntity.ok(response);
    }

    /**
     * Tìm kiếm categories theo tên
     * GET /api/v1/categories/search?name=keyword
     */
    @GetMapping("/search")
    @Operation(
        summary = "Tìm kiếm categories theo từ khóa", 
        description = "Tìm kiếm categories có tên chứa từ khóa được cung cấp (partial match). " +
                     "Không phân biệt hoa thường, trả về danh sách tất cả categories phù hợp.",
        tags = {"Category Management"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Tìm kiếm thành công",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = List.class),
                examples = @ExampleObject(
                    name = "Success Response",
                    value = """
                    [
                        {
                            "id": "123e4567-e89b-12d3-a456-426614174000",
                            "name": "Rau củ quả",
                            "description": "Các loại rau củ quả tươi",
                            "isActive": true,
                            "createdAt": "2024-01-15T10:30:00Z",
                            "updatedAt": "2024-01-15T10:30:00Z"
                        },
                        {
                            "id": "789e0123-e89b-12d3-a456-426614174002",
                            "name": "Rau xanh",
                            "description": "Các loại rau xanh tươi",
                            "isActive": true,
                            "createdAt": "2024-01-15T12:00:00Z",
                            "updatedAt": "2024-01-15T12:00:00Z"
                        }
                    ]
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Từ khóa tìm kiếm không hợp lệ",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Bad Request Error",
                    value = """
                    {
                        "success": false,
                        "message": "Từ khóa tìm kiếm không được để trống",
                        "data": null
                    }
                    """
                )
            )
        )
    })
    public ResponseEntity<List<CategoryResponse>> searchCategoriesByName(
            @Parameter(
                description = "Từ khóa để tìm kiếm trong tên category",
                required = true,
                example = "rau"
            )
            @RequestParam String name) {
        log.info("Searching categories by name: {}", name);
        List<CategoryResponse> response = categoryService.searchCategoriesByName(name);
        return ResponseEntity.ok(response);
    }

    /**
     * Cập nhật category
     * PUT /api/v1/categories/{id}
     */
    @PutMapping("/{id}")
    @Operation(
        summary = "Cập nhật thông tin category", 
        description = "Cập nhật thông tin của một category đã tồn tại. " +
                     "Có thể cập nhật tên, mô tả và trạng thái của category.",
        tags = {"Category Management"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Cập nhật category thành công",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CategoryResponse.class),
                examples = @ExampleObject(
                    name = "Success Response",
                    value = """
                    {
                        "id": "123e4567-e89b-12d3-a456-426614174000",
                        "name": "Rau củ quả tươi",
                        "description": "Các loại rau củ quả tươi ngon",
                        "isActive": true,
                        "createdAt": "2024-01-15T10:30:00Z",
                        "updatedAt": "2024-01-15T14:30:00Z"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Không tìm thấy category với ID này",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Not Found Error",
                    value = """
                    {
                        "success": false,
                        "message": "Không tìm thấy category với ID: 123e4567-e89b-12d3-a456-426614174000",
                        "data": null
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Dữ liệu đầu vào không hợp lệ",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Validation Error",
                    value = """
                    {
                        "success": false,
                        "message": "Tên category không được để trống",
                        "data": null
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "409", 
            description = "Tên category mới đã tồn tại",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Conflict Error",
                    value = """
                    {
                        "success": false,
                        "message": "Tên category này đã tồn tại",
                        "data": null
                    }
                    """
                )
            )
        )
    })
    public ResponseEntity<CategoryResponse> updateCategory(
            @Parameter(
                description = "UUID của category cần cập nhật",
                required = true,
                example = "123e4567-e89b-12d3-a456-426614174000"
            )
            @PathVariable UUID id, 
            @Parameter(
                description = "Thông tin cập nhật cho category",
                required = true,
                schema = @Schema(implementation = CategoryUpdateRequest.class)
            )
            @Valid @RequestBody CategoryUpdateRequest request) {
        log.info("Updating category with ID: {}", id);
        CategoryResponse response = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Xóa category (soft delete)
     * DELETE /api/v1/categories/{id}
     */
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Xóa category (soft delete)", 
        description = "Thực hiện soft delete cho category. Category sẽ không bị xóa hoàn toàn " +
                     "khỏi database mà chỉ được đánh dấu là inactive.",
        tags = {"Category Management"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204", 
            description = "Xóa category thành công"
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Không tìm thấy category với ID này",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Not Found Error",
                    value = """
                    {
                        "success": false,
                        "message": "Không tìm thấy category với ID: 123e4567-e89b-12d3-a456-426614174000",
                        "data": null
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "ID không hợp lệ",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Invalid ID Error",
                    value = """
                    {
                        "success": false,
                        "message": "ID không hợp lệ",
                        "data": null
                    }
                    """
                )
            )
        )
    })
    public ResponseEntity<Void> deleteCategory(
            @Parameter(
                description = "UUID của category cần xóa",
                required = true,
                example = "123e4567-e89b-12d3-a456-426614174000"
            )
            @PathVariable UUID id) {
        log.info("Deleting category with ID: {}", id);
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Kích hoạt category
     * PUT /api/v1/categories/{id}/activate
     */
    @PutMapping("/{id}/activate")
    @Operation(
        summary = "Kích hoạt category", 
        description = "Kích hoạt một category đã bị vô hiệu hóa. " +
                     "Category sẽ có trạng thái isActive = true và có thể được sử dụng.",
        tags = {"Category Management"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Kích hoạt category thành công",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CategoryResponse.class),
                examples = @ExampleObject(
                    name = "Success Response",
                    value = """
                    {
                        "id": "123e4567-e89b-12d3-a456-426614174000",
                        "name": "Rau củ quả",
                        "description": "Các loại rau củ quả tươi",
                        "isActive": true,
                        "createdAt": "2024-01-15T10:30:00Z",
                        "updatedAt": "2024-01-15T15:30:00Z"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Không tìm thấy category với ID này",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Not Found Error",
                    value = """
                    {
                        "success": false,
                        "message": "Không tìm thấy category với ID: 123e4567-e89b-12d3-a456-426614174000",
                        "data": null
                    }
                    """
                )
            )
        )
    })
    public ResponseEntity<CategoryResponse> activateCategory(
            @Parameter(
                description = "UUID của category cần kích hoạt",
                required = true,
                example = "123e4567-e89b-12d3-a456-426614174000"
            )
            @PathVariable UUID id) {
        log.info("Activating category with ID: {}", id);
        CategoryResponse response = categoryService.activateCategory(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Vô hiệu hóa category
     * PUT /api/v1/categories/{id}/deactivate
     */
    @PutMapping("/{id}/deactivate")
    @Operation(
        summary = "Vô hiệu hóa category", 
        description = "Vô hiệu hóa một category đang hoạt động. " +
                     "Category sẽ có trạng thái isActive = false và không thể được sử dụng.",
        tags = {"Category Management"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Vô hiệu hóa category thành công",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CategoryResponse.class),
                examples = @ExampleObject(
                    name = "Success Response",
                    value = """
                    {
                        "id": "123e4567-e89b-12d3-a456-426614174000",
                        "name": "Rau củ quả",
                        "description": "Các loại rau củ quả tươi",
                        "isActive": false,
                        "createdAt": "2024-01-15T10:30:00Z",
                        "updatedAt": "2024-01-15T16:30:00Z"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Không tìm thấy category với ID này",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Not Found Error",
                    value = """
                    {
                        "success": false,
                        "message": "Không tìm thấy category với ID: 123e4567-e89b-12d3-a456-426614174000",
                        "data": null
                    }
                    """
                )
            )
        )
    })
    public ResponseEntity<CategoryResponse> deactivateCategory(
            @Parameter(
                description = "UUID của category cần vô hiệu hóa",
                required = true,
                example = "123e4567-e89b-12d3-a456-426614174000"
            )
            @PathVariable UUID id) {
        log.info("Deactivating category with ID: {}", id);
        CategoryResponse response = categoryService.deactivateCategory(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Kiểm tra category có tồn tại không
     * GET /api/v1/categories/{id}/exists
     */
    @GetMapping("/{id}/exists")
    @Operation(
        summary = "Kiểm tra category có tồn tại theo ID", 
        description = "Kiểm tra xem category có tồn tại trong hệ thống hay không dựa trên ID. " +
                     "Trả về true nếu tồn tại, false nếu không tồn tại.",
        tags = {"Category Management"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Kiểm tra thành công",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(type = "boolean"),
                examples = @ExampleObject(
                    name = "Category Exists",
                    value = "true"
                )
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "ID không hợp lệ",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Invalid ID",
                    value = "false"
                )
            )
        )
    })
    public ResponseEntity<Boolean> existsById(
            @Parameter(
                description = "UUID của category cần kiểm tra",
                required = true,
                example = "123e4567-e89b-12d3-a456-426614174000"
            )
            @PathVariable UUID id) {
        log.info("Checking if category exists with ID: {}", id);
        boolean exists = categoryService.existsById(id);
        return ResponseEntity.ok(exists);
    }

    /**
     * Kiểm tra category có tồn tại theo tên không
     * GET /api/v1/categories/exists?name=categoryName
     */
    @GetMapping("/exists")
    @Operation(
        summary = "Kiểm tra category có tồn tại theo tên", 
        description = "Kiểm tra xem category có tồn tại trong hệ thống hay không dựa trên tên. " +
                     "Trả về true nếu tồn tại, false nếu không tồn tại.",
        tags = {"Category Management"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Kiểm tra thành công",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(type = "boolean"),
                examples = @ExampleObject(
                    name = "Category Exists",
                    value = "true"
                )
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Tên category không hợp lệ",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Invalid Name",
                    value = "false"
                )
            )
        )
    })
    public ResponseEntity<Boolean> existsByName(
            @Parameter(
                description = "Tên của category cần kiểm tra",
                required = true,
                example = "Rau củ quả"
            )
            @RequestParam String name) {
        log.info("Checking if category exists with name: {}", name);
        boolean exists = categoryService.existsByName(name);
        return ResponseEntity.ok(exists);
    }

    /**
     * Đếm số categories đang hoạt động
     * GET /api/v1/categories/count/active
     */
    @GetMapping("/count/active")
    @Operation(
        summary = "Đếm số categories đang hoạt động", 
        description = "Đếm tổng số categories có trạng thái active (isActive = true) trong hệ thống. " +
                     "Trả về số lượng dưới dạng Long.",
        tags = {"Category Management"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Đếm thành công",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(type = "integer", format = "int64"),
                examples = @ExampleObject(
                    name = "Count Result",
                    value = "15"
                )
            )
        )
    })
    public ResponseEntity<Long> countActiveCategories() {
        log.info("Counting active categories");
        long count = categoryService.countActiveCategories();
        return ResponseEntity.ok(count);
    }
}