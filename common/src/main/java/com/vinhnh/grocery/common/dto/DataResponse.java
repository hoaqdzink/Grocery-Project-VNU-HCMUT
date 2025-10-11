package com.vinhnh.grocery.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Standard Data Response wrapper
 * Cung cấp format nhất quán cho tất cả API responses
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<T> {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;
    private String path;
    private Object errors;

    /**
     * Tạo success response với data
     */
    public static <T> DataResponse<T> success(T data) {
        return DataResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(200)
                .message("Success")
                .data(data)
                .build();
    }

    /**
     * Tạo success response với message
     */
    public static <T> DataResponse<T> success(String message, T data) {
        return DataResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(200)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * Tạo success response không có data
     */
    public static <T> DataResponse<T> success(String message) {
        return DataResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(200)
                .message(message)
                .build();
    }

    /**
     * Tạo created response
     */
    public static <T> DataResponse<T> created(T data) {
        return DataResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(201)
                .message("Created successfully")
                .data(data)
                .build();
    }

    /**
     * Tạo created response với message
     */
    public static <T> DataResponse<T> created(String message, T data) {
        return DataResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(201)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * Tạo error response
     */
    public static <T> DataResponse<T> error(int status, String message) {
        return DataResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .message(message)
                .build();
    }

    /**
     * Tạo error response với errors
     */
    public static <T> DataResponse<T> error(int status, String message, Object errors) {
        return DataResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .message(message)
                .errors(errors)
                .build();
    }

    /**
     * Tạo bad request response
     */
    public static <T> DataResponse<T> badRequest(String message) {
        return error(400, message);
    }

    /**
     * Tạo bad request response với errors
     */
    public static <T> DataResponse<T> badRequest(String message, Object errors) {
        return error(400, message, errors);
    }

    /**
     * Tạo not found response
     */
    public static <T> DataResponse<T> notFound(String message) {
        return error(404, message);
    }

    /**
     * Tạo internal server error response
     */
    public static <T> DataResponse<T> internalServerError(String message) {
        return error(500, message);
    }
}