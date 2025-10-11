package com.vinhnh.grocery.common.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Custom AuditorAware để tự động lấy thông tin user hiện tại
 * 
 * TODO: Khi implement Spring Security, sẽ sử dụng SecurityContextHolder
 * Hiện tại trả về "SYSTEM" cho tất cả operations
 */
public class CustomAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO: Implement with Spring Security
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // return Optional.of(authentication.getName());
        
        // Temporary implementation - return SYSTEM for all operations
        return Optional.of("SYSTEM");
    }
}
