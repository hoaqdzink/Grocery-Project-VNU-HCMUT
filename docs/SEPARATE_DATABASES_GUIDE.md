# 🗄️ Separate Databases Guide

Hướng dẫn sử dụng Docker Compose với từng PostgreSQL service riêng biệt.

## 📋 Tổng quan

Thay vì sử dụng một PostgreSQL với multiple databases, giờ đây mỗi microservice có một PostgreSQL instance riêng biệt:

### 🏗️ **Kiến trúc mới:**
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│ Product Service │───▶│ PostgreSQL      │    │ Port: 5432      │
│                 │    │ Product         │    │ DB: product_db  │
└─────────────────┘    └─────────────────┘    └─────────────────┘

┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│ User Service    │───▶│ PostgreSQL      │    │ Port: 5433      │
│                 │    │ User            │    │ DB: user_db     │
└─────────────────┘    └─────────────────┘    └─────────────────┘

┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│ Cart Service    │───▶│ PostgreSQL      │    │ Port: 5434      │
│                 │    │ Cart            │    │ DB: cart_db     │
└─────────────────┘    └─────────────────┘    └─────────────────┘

┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│ Order Service   │───▶│ PostgreSQL      │    │ Port: 5435      │
│                 │    │ Order           │    │ DB: order_db    │
└─────────────────┘    └─────────────────┘    └─────────────────┘

┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│ Payment Service │───▶│ PostgreSQL      │    │ Port: 5436      │
│                 │    │ Payment         │    │ DB: payment_db  │
└─────────────────┘    └─────────────────┘    └─────────────────┘

┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│ Notification    │───▶│ PostgreSQL      │    │ Port: 5437      │
│ Service         │    │ Notification    │    │ DB: notification│
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 🚀 Cách sử dụng

### 1. **Tạo file .env**
```bash
# Copy file mẫu
cp environment-variables-separate-db.env .env

# Chỉnh sửa file .env
nano .env
```

### 2. **Chạy tất cả services**
```bash
# Chạy tất cả PostgreSQL services và infrastructure
docker-compose up -d

# Hoặc chạy từng nhóm services
docker-compose up -d postgres-product postgres-user postgres-cart
docker-compose up -d postgres-order postgres-payment postgres-notification
```

### 3. **Chạy từng service riêng lẻ**
```bash
# Chỉ chạy Product Service với database
docker-compose up -d postgres-product

# Chỉ chạy User Service với database
docker-compose up -d postgres-user

# Chỉ chạy Cart Service với database
docker-compose up -d postgres-cart
```

## 🔧 Cấu hình Database Connections

### **Product Service**
```yaml
# Trong application.properties của Product Service
spring.datasource.url=jdbc:postgresql://postgres-product:5432/product_db
spring.datasource.username=grocery_user
spring.datasource.password=grocery_password
```

### **User Service**
```yaml
# Trong application.properties của User Service
spring.datasource.url=jdbc:postgresql://postgres-user:5432/user_db
spring.datasource.username=grocery_user
spring.datasource.password=grocery_password
```

### **Cart Service**
```yaml
# Trong application.properties của Cart Service
spring.datasource.url=jdbc:postgresql://postgres-cart:5432/cart_db
spring.datasource.username=grocery_user
spring.datasource.password=grocery_password
```

### **Order Service**
```yaml
# Trong application.properties của Order Service
spring.datasource.url=jdbc:postgresql://postgres-order:5432/order_db
spring.datasource.username=grocery_user
spring.datasource.password=grocery_password
```

### **Payment Service**
```yaml
# Trong application.properties của Payment Service
spring.datasource.url=jdbc:postgresql://postgres-payment:5432/payment_db
spring.datasource.username=grocery_user
spring.datasource.password=grocery_password
```

### **Notification Service**
```yaml
# Trong application.properties của Notification Service
spring.datasource.url=jdbc:postgresql://postgres-notification:5432/notification_db
spring.datasource.username=grocery_user
spring.datasource.password=grocery_password
```

## 📊 Port Mapping

| Service | Container Name | External Port | Internal Port | Database |
|---------|----------------|---------------|---------------|----------|
| Product | postgres-product | 5432 | 5432 | product_db |
| User | postgres-user | 5433 | 5432 | user_db |
| Cart | postgres-cart | 5434 | 5432 | cart_db |
| Order | postgres-order | 5435 | 5432 | order_db |
| Payment | postgres-payment | 5436 | 5432 | payment_db |
| Notification | postgres-notification | 5437 | 5432 | notification_db |

## 🔍 Kiểm tra kết nối

### **Kiểm tra từng database**
```bash
# Product Database
docker exec -it grocery-postgres-product psql -U grocery_user -d product_db

# User Database
docker exec -it grocery-postgres-user psql -U grocery_user -d user_db

# Cart Database
docker exec -it grocery-postgres-cart psql -U grocery_user -d cart_db

# Order Database
docker exec -it grocery-postgres-order psql -U grocery_user -d order_db

# Payment Database
docker exec -it grocery-postgres-payment psql -U grocery_user -d payment_db

# Notification Database
docker exec -it grocery-postgres-notification psql -U grocery_user -d notification_db
```

### **Kiểm tra từ host machine**
```bash
# Product Database
psql -h localhost -p 5432 -U grocery_user -d product_db

# User Database
psql -h localhost -p 5433 -U grocery_user -d user_db

# Cart Database
psql -h localhost -p 5434 -U grocery_user -d cart_db

# Order Database
psql -h localhost -p 5435 -U grocery_user -d order_db

# Payment Database
psql -h localhost -p 5436 -U grocery_user -d payment_db

# Notification Database
psql -h localhost -p 5437 -U grocery_user -d notification_db
```

## 🛠️ Management Commands

### **Start/Stop từng database**
```bash
# Start Product Database
docker-compose start postgres-product

# Stop User Database
docker-compose stop postgres-user

# Restart Cart Database
docker-compose restart postgres-cart
```

### **Xem logs từng database**
```bash
# Xem logs Product Database
docker-compose logs -f postgres-product

# Xem logs User Database
docker-compose logs -f postgres-user

# Xem logs tất cả databases
docker-compose logs -f postgres-product postgres-user postgres-cart
```

### **Backup từng database**
```bash
# Backup Product Database
docker exec grocery-postgres-product pg_dump -U grocery_user product_db > product_backup.sql

# Backup User Database
docker exec grocery-postgres-user pg_dump -U grocery_user user_db > user_backup.sql

# Backup tất cả databases
for service in product user cart order payment notification; do
  docker exec grocery-postgres-$service pg_dump -U grocery_user ${service}_db > ${service}_backup.sql
done
```

## ✅ Lợi ích của Separate Databases

### **1. Isolation**
- Mỗi service có database riêng biệt
- Không ảnh hưởng lẫn nhau khi có lỗi
- Dễ dàng scale từng service

### **2. Security**
- Mỗi service chỉ có quyền truy cập database của mình
- Giảm thiểu rủi ro bảo mật
- Dễ dàng audit và monitoring

### **3. Performance**
- Không có contention giữa các services
- Có thể tune performance riêng cho từng database
- Dễ dàng optimize queries

### **4. Maintenance**
- Dễ dàng backup/restore từng database
- Có thể upgrade từng database riêng biệt
- Dễ dàng migrate data

### **5. Development**
- Developers có thể làm việc độc lập
- Không cần phối hợp khi thay đổi schema
- Dễ dàng test từng service

## ⚠️ Lưu ý

### **Resource Usage**
- Sử dụng nhiều RAM hơn (mỗi PostgreSQL instance ~100MB)
- Cần nhiều disk space hơn
- Cần quản lý nhiều connections

### **Complexity**
- Phức tạp hơn trong việc setup
- Cần quản lý nhiều databases
- Cần backup strategy cho từng database

### **Cross-Service Queries**
- Không thể join data trực tiếp giữa các services
- Cần sử dụng API calls hoặc event-driven architecture
- Cần implement data consistency patterns

## 🎯 Best Practices

1. **Sử dụng connection pooling** cho mỗi service
2. **Implement circuit breaker** cho database connections
3. **Monitor performance** của từng database
4. **Backup định kỳ** cho tất cả databases
5. **Sử dụng health checks** để monitor database status
6. **Implement retry logic** cho database operations
7. **Sử dụng read replicas** nếu cần thiết
