# ==================== USER SERVICE DOCKERFILE ====================
# Multi-stage build cho User Service
# Stage 1: Build application
FROM gradle:8.10.2-jdk21 AS builder

# Set working directory
WORKDIR /app

# Copy file cấu hình Gradle
COPY build.gradle .
COPY settings.gradle .

# Copy source code
COPY src/ src/

# Build application (không chạy test)
RUN gradle clean build -x test

# Stage 2: Runtime image
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /app

# Create non-root user
RUN groupadd -r grocery && useradd -r -g grocery grocery

# Copy JAR file from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Change ownership to non-root user
RUN chown -R grocery:grocery /app

# Switch to non-root user
USER grocery

# Expose port
EXPOSE 8082

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8082/actuator/health || exit 1

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
