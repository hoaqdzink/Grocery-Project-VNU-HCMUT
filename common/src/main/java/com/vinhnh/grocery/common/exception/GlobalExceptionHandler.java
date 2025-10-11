package com.vinhnh.grocery.common.exception;

import com.vinhnh.grocery.common.dto.DataResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global Exception Handler
 * Xử lý tất cả exceptions trong ứng dụng một cách nhất quán
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Xử lý validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DataResponse<Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error("Validation error: {}", ex.getMessage());
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        DataResponse<Object> response = DataResponse.badRequest("Dữ liệu đầu vào không hợp lệ", errors);
        response.setPath(request.getRequestURI());
        
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Xử lý IllegalArgumentException (business logic errors)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DataResponse<Object>> handleIllegalArgumentException(
            IllegalArgumentException ex, HttpServletRequest request) {
        log.error("Business logic error: {}", ex.getMessage());
        
        DataResponse<Object> response = DataResponse.badRequest(ex.getMessage());
        response.setPath(request.getRequestURI());
        
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Xử lý RuntimeException
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<DataResponse<Object>> handleRuntimeException(
            RuntimeException ex, HttpServletRequest request) {
        log.error("Runtime error: {}", ex.getMessage(), ex);
        
        DataResponse<Object> response = DataResponse.internalServerError("Đã xảy ra lỗi hệ thống");
        response.setPath(request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Xử lý tất cả exceptions khác
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DataResponse<Object>> handleGenericException(
            Exception ex, HttpServletRequest request) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        
        DataResponse<Object> response = DataResponse.internalServerError("Đã xảy ra lỗi không mong muốn");
        response.setPath(request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
