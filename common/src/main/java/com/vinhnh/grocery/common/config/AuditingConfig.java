package com.vinhnh.grocery.common.config;

import com.vinhnh.grocery.common.audit.CustomAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Configuration class để enable JPA Auditing
 * Tự động quản lý created_at, updated_at, created_by, updated_by
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditingConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new CustomAuditorAware();
    }
}
