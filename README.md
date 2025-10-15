# 🛒 Grocery Project - VNU HCMUT

Dự án hệ thống quản lý cửa hàng tạp hóa trực tuyến được phát triển bởi Nguyễn Hoàng Vinh sinh viên Đại học Bách Khoa TP.HCM.

## 📋 Tổng quan dự án

Grocery Project là một hệ thống microservices được xây dựng bằng Spring Boot, cung cấp đầy đủ các chức năng cho một cửa hàng tạp hóa trực tuyến bao gồm quản lý sản phẩm, người dùng, giỏ hàng, đơn hàng và thanh toán.

## 🏗️ Kiến trúc hệ thống

```
Grocery-Project-VNU-HCMUT/
├── api-gateway/             # API Gateway - Entry point cho tất cả requests
│   ├── src/main/java/       # Source code Java
│   ├── src/main/resources/  # Configuration files
│   ├── build.gradle         # Gradle build configuration
│   └── Dockerfile           # Docker configuration
├── product-service/          # Service quản lý sản phẩm
│   ├── src/main/java/       # Source code Java
│   ├── src/main/resources/  # Configuration files
│   ├── build.gradle         # Gradle build configuration
│   └── Dockerfile           # Docker configuration
├── user-service/            # Service quản lý người dùng
│   ├── src/main/java/       # Source code Java
│   ├── src/main/resources/  # Configuration files
│   └── build.gradle         # Gradle build configuration
├── cart-service/            # Service quản lý giỏ hàng
│   ├── src/main/java/       # Source code Java
│   ├── src/main/resources/  # Configuration files
│   └── build.gradle         # Gradle build configuration
├── order-service/           # Service quản lý đơn hàng
│   ├── src/main/java/       # Source code Java
│   ├── src/main/resources/  # Configuration files
│   └── build.gradle         # Gradle build configuration
├── payment-service/         # Service xử lý thanh toán
│   ├── src/main/java/       # Source code Java
│   ├── src/main/resources/  # Configuration files
│   └── build.gradle         # Gradle build configuration
├── notification-service/    # Service gửi thông báo
│   ├── src/main/java/       # Source code Java
│   ├── src/main/resources/  # Configuration files
│   └── build.gradle         # Gradle build configuration
├── common/                  # Shared libraries và utilities
├── build.gradle            # Root build configuration
├── settings.gradle         # Gradle settings
└── README.md
```

## 🚀 Các service trong hệ thống

### 🌐 API Gateway
- **Mô tả**: Entry point duy nhất cho tất cả client requests, xử lý routing, load balancing, và cross-cutting concerns
- **Công nghệ**: Spring Boot 3.4.10, Spring Cloud Gateway, Java 21, Gradle
- **Port**: 8080
- **API**: Single entry point cho tất cả microservices
- **Chức năng**:
  - Request routing và load balancing
  - Authentication và authorization
  - Rate limiting và throttling
  - Request/Response logging
  - Circuit breaker pattern
  - CORS handling
  - API versioning

### 🛍️ Product Service
- **Mô tả**: Service quản lý thông tin sản phẩm, danh mục, và kho hàng
- **Công nghệ**: Spring Boot 3.4.10, Java 21, Gradle
- **Port**: 8081
- **API**: RESTful APIs cho CRUD operations sản phẩm
- **Chức năng**: 
  - Quản lý danh sách sản phẩm
  - Tìm kiếm và lọc sản phẩm
  - Quản lý danh mục sản phẩm
  - Theo dõi tồn kho

### 👤 User Service
- **Mô tả**: Service quản lý thông tin người dùng và xác thực
- **Công nghệ**: Spring Boot 3.4.10, Java 21, Gradle
- **Port**: 8082
- **API**: RESTful APIs cho quản lý người dùng
- **Chức năng**:
  - Đăng ký/Đăng nhập người dùng
  - Quản lý profile
  - Xác thực và phân quyền
  - Quản lý địa chỉ giao hàng

### 🛒 Cart Service
- **Mô tả**: Service quản lý giỏ hàng của người dùng
- **Công nghệ**: Spring Boot 3.4.10, Java 21, Gradle
- **Port**: 8083
- **API**: RESTful APIs cho quản lý giỏ hàng
- **Chức năng**:
  - Thêm/xóa sản phẩm khỏi giỏ hàng
  - Cập nhật số lượng sản phẩm
  - Tính toán tổng tiền
  - Lưu trữ giỏ hàng tạm thời

### 📦 Order Service
- **Mô tả**: Service quản lý đơn hàng và quy trình đặt hàng
- **Công nghệ**: Spring Boot 3.4.10, Java 21, Gradle
- **Port**: 8084
- **API**: RESTful APIs cho quản lý đơn hàng
- **Chức năng**:
  - Tạo đơn hàng từ giỏ hàng
  - Theo dõi trạng thái đơn hàng
  - Quản lý lịch sử đơn hàng
  - Xử lý hủy đơn hàng

### 💳 Payment Service
- **Mô tả**: Service xử lý thanh toán và quản lý giao dịch
- **Công nghệ**: Spring Boot 3.4.10, Java 21, Gradle
- **Port**: 8085
- **API**: RESTful APIs cho xử lý thanh toán
- **Chức năng**:
  - Xử lý thanh toán online
  - Quản lý các phương thức thanh toán
  - Theo dõi trạng thái giao dịch
  - Hoàn tiền và xử lý lỗi

### 📧 Notification Service
- **Mô tả**: Service gửi thông báo qua email, SMS, push notification
- **Công nghệ**: Spring Boot 3.4.10, Java 21, Gradle
- **Port**: 8086
- **API**: RESTful APIs cho gửi thông báo
- **Chức năng**:
  - Gửi email thông báo
  - Gửi SMS
  - Push notification
  - Template management
  - Notification history
  - Batch notifications
  - Real-time notifications

### 🔧 Common Module
- **Mô tả**: Thư viện chung chứa các utility và shared classes
- **Công nghệ**: Java 21, Gradle
- **Chức năng**:
  - DTOs và Entities chung
  - Exception handling
  - Utility classes
  - Configuration classes

## 🛠️ Công nghệ sử dụng

- **Backend**: Spring Boot 3.4.10
- **Language**: Java 21
- **Build Tool**: Gradle 8.14.3+
- **Database**: PostgreSQL (được cấu hình trong Docker)
- **Containerization**: Docker & Docker Compose
- **API**: RESTful APIs
- **Testing**: JUnit 5, TestContainers
- **Architecture**: Microservices
- **Communication**: HTTP/REST APIs

## 📦 Cài đặt và chạy dự án

### Yêu cầu hệ thống
- Java 21+
- Docker & Docker Compose
- Gradle 8.14.3+
- Git

### 1. Clone repository
```bash
git clone <repository-url>
cd Grocery-Project-VNU-HCMUT
```

### 2. Chạy tất cả services

#### Cách 1: Chạy từng service riêng lẻ
```bash
# Product Service
cd product-service
./gradlew bootRun

# API Gateway (terminal mới)
cd api-gateway
./gradlew bootRun

# User Service (terminal mới)
cd user-service
./gradlew bootRun

# Cart Service (terminal mới)
cd cart-service
./gradlew bootRun

# Order Service (terminal mới)
cd order-service
./gradlew bootRun

# Payment Service (terminal mới)
cd payment-service
./gradlew bootRun

# Notification Service (terminal mới)
cd notification-service
./gradlew bootRun
```

#### Cách 2: Build tất cả services
```bash
# Build tất cả services
./gradlew build

# Hoặc build từng service
./gradlew :api-gateway:build
./gradlew :product-service:build
./gradlew :user-service:build
./gradlew :cart-service:build
./gradlew :order-service:build
./gradlew :payment-service:build
./gradlew :notification-service:build
```

#### Cách 3: Sử dụng Docker Compose

##### Phiên bản đơn giản (Recommended cho development)
```bash
# Chạy với docker-compose đơn giản (chỉ PostgreSQL + Redis)
docker-compose -f docker-compose.simple.yml up -d

# Xem logs
docker-compose -f docker-compose.simple.yml logs -f

# Stop services
docker-compose -f docker-compose.simple.yml down
```

##### Phiên bản đầy đủ (với Kafka, Prometheus, Grafana)
```bash
# 1. Cấu hình environment variables
cp environment.properties .env
# Chỉnh sửa file .env với thông tin thực tế của bạn

# 2. Chạy tất cả services với Docker Compose
docker-compose up -d

# 3. Xem logs
docker-compose logs -f

# 4. Stop tất cả services
docker-compose down

# 5. Chạy chỉ infrastructure services (PostgreSQL, Kafka, Redis)
docker-compose up -d postgres kafka redis
```

### 3. Kiểm tra các services

#### Application Services
- **API Gateway**: `http://localhost:8080/actuator/health`
- **Product Service**: `http://localhost:8081/actuator/health`
- **User Service**: `http://localhost:8082/actuator/health`
- **Cart Service**: `http://localhost:8083/actuator/health`
- **Order Service**: `http://localhost:8084/actuator/health`
- **Payment Service**: `http://localhost:8085/actuator/health`
- **Notification Service**: `http://localhost:8086/actuator/health`

#### Infrastructure Services

##### Phiên bản đơn giản
- **PostgreSQL**: `localhost:5432`
- **Redis**: `localhost:6379`

##### Phiên bản đầy đủ
- **PostgreSQL**: `localhost:5432`
- **Kafka**: `localhost:9092`
- **Redis**: `localhost:6379`
- **Kafka UI**: `http://localhost:8080` (Kafka UI)
- **Prometheus**: `http://localhost:9090`
- **Grafana**: `http://localhost:3000` (admin/admin)

## 🔧 Cấu hình

### Environment Variables
Tạo file `.env` từ `environment.properties` và cấu hình các biến sau:

#### Database Configuration
- `POSTGRES_DB`: Tên database chính
- `POSTGRES_USER`: Username database
- `POSTGRES_PASSWORD`: Password database

#### Kafka Configuration
- `KAFKA_BOOTSTRAP_SERVERS`: Kafka broker addresses
- `KAFKA_ZOOKEEPER_CONNECT`: Zookeeper connection string

#### Email Configuration
- `EMAIL_USERNAME`: Gmail username
- `EMAIL_PASSWORD`: Gmail app password
- `EMAIL_HOST`: SMTP host (smtp.gmail.com)
- `EMAIL_PORT`: SMTP port (587)

#### JWT Configuration
- `JWT_SECRET`: Secret key cho JWT tokens
- `JWT_EXPIRATION`: Token expiration time

#### External Services
- `STRIPE_PUBLIC_KEY`: Stripe public key
- `STRIPE_SECRET_KEY`: Stripe secret key
- `SMS_API_KEY`: Twilio account SID
- `SMS_API_SECRET`: Twilio auth token

### Profiles
- `dev`: Môi trường development
- `prod`: Môi trường production
- `docker`: Môi trường Docker

### Infrastructure Services

#### Phiên bản đơn giản (docker-compose.simple.yml)
- **PostgreSQL**: Database chính cho tất cả services
- **Redis**: Caching và session management

#### Phiên bản đầy đủ (docker-compose.yml)
- **PostgreSQL**: Database chính với multiple databases
- **Kafka**: Message broker cho event-driven architecture
- **Redis**: Caching và session management
- **Prometheus**: Metrics collection
- **Grafana**: Monitoring dashboard

## 📚 API Documentation

### 🌐 API Gateway Routes (Port: 8080)

Tất cả các API calls sẽ đi qua API Gateway với các routes sau:

#### Product Service Routes
```http
GET /api/products/*          → Product Service (8081)
POST /api/products/*         → Product Service (8081)
PUT /api/products/*          → Product Service (8081)
DELETE /api/products/*       → Product Service (8081)
```

#### User Service Routes
```http
POST /api/users/register     → User Service (8082)
POST /api/users/login        → User Service (8082)
GET /api/users/profile       → User Service (8082)
PUT /api/users/profile       → User Service (8082)
```

#### Cart Service Routes
```http
GET /api/cart/*              → Cart Service (8083)
POST /api/cart/*             → Cart Service (8083)
PUT /api/cart/*              → Cart Service (8083)
DELETE /api/cart/*           → Cart Service (8083)
```

#### Order Service Routes
```http
GET /api/orders/*            → Order Service (8084)
POST /api/orders/*           → Order Service (8084)
PUT /api/orders/*            → Order Service (8084)
```

#### Payment Service Routes
```http
POST /api/payments/*         → Payment Service (8085)
GET /api/payments/*          → Payment Service (8085)
```

#### Notification Service Routes
```http
POST /api/notifications/*    → Notification Service (8086)
GET /api/notifications/*     → Notification Service (8086)
PUT /api/notifications/*     → Notification Service (8086)
DELETE /api/notifications/*  → Notification Service (8086)
```

### 🛍️ Product Service APIs (Port: 8081)

#### Lấy danh sách sản phẩm
```http
GET /api/products
```

#### Lấy thông tin sản phẩm theo ID
```http
GET /api/products/{id}
```

#### Tạo sản phẩm mới
```http
POST /api/products
Content-Type: application/json

{
  "name": "Tên sản phẩm",
  "description": "Mô tả sản phẩm",
  "price": 100000,
  "category": "Danh mục",
  "stock": 50
}
```

#### Cập nhật sản phẩm
```http
PUT /api/products/{id}
Content-Type: application/json

{
  "name": "Tên sản phẩm cập nhật",
  "price": 120000
}
```

#### Xóa sản phẩm
```http
DELETE /api/products/{id}
```

### 👤 User Service APIs (Port: 8082)

#### Đăng ký người dùng
```http
POST /api/users/register
Content-Type: application/json

{
  "username": "username",
  "email": "user@example.com",
  "password": "password123",
  "fullName": "Họ và tên"
}
```

#### Đăng nhập
```http
POST /api/users/login
Content-Type: application/json

{
  "username": "username",
  "password": "password123"
}
```

#### Lấy thông tin profile
```http
GET /api/users/profile
Authorization: Bearer {token}
```

#### Cập nhật profile
```http
PUT /api/users/profile
Authorization: Bearer {token}
Content-Type: application/json

{
  "fullName": "Tên mới",
  "email": "newemail@example.com"
}
```

### 🛒 Cart Service APIs (Port: 8083)

#### Lấy giỏ hàng
```http
GET /api/cart
Authorization: Bearer {token}
```

#### Thêm sản phẩm vào giỏ hàng
```http
POST /api/cart/items
Authorization: Bearer {token}
Content-Type: application/json

{
  "productId": 1,
  "quantity": 2
}
```

#### Cập nhật số lượng sản phẩm
```http
PUT /api/cart/items/{itemId}
Authorization: Bearer {token}
Content-Type: application/json

{
  "quantity": 3
}
```

#### Xóa sản phẩm khỏi giỏ hàng
```http
DELETE /api/cart/items/{itemId}
Authorization: Bearer {token}
```

### 📦 Order Service APIs (Port: 8084)

#### Tạo đơn hàng
```http
POST /api/orders
Authorization: Bearer {token}
Content-Type: application/json

{
  "shippingAddress": {
    "street": "123 Đường ABC",
    "city": "TP.HCM",
    "district": "Quận 1"
  },
  "paymentMethod": "CREDIT_CARD"
}
```

#### Lấy danh sách đơn hàng
```http
GET /api/orders
Authorization: Bearer {token}
```

#### Lấy chi tiết đơn hàng
```http
GET /api/orders/{orderId}
Authorization: Bearer {token}
```

#### Cập nhật trạng thái đơn hàng
```http
PUT /api/orders/{orderId}/status
Authorization: Bearer {token}
Content-Type: application/json

{
  "status": "SHIPPED"
}
```

### 💳 Payment Service APIs (Port: 8085)

#### Xử lý thanh toán
```http
POST /api/payments/process
Authorization: Bearer {token}
Content-Type: application/json

{
  "orderId": 1,
  "amount": 500000,
  "paymentMethod": "CREDIT_CARD",
  "cardNumber": "1234567890123456",
  "expiryDate": "12/25",
  "cvv": "123"
}
```

#### Lấy lịch sử thanh toán
```http
GET /api/payments/history
Authorization: Bearer {token}
```

#### Hoàn tiền
```http
POST /api/payments/{paymentId}/refund
Authorization: Bearer {token}
Content-Type: application/json

{
  "reason": "Sản phẩm bị lỗi"
}
```

### 📧 Notification Service APIs (Port: 8086)

#### Gửi email thông báo
```http
POST /api/notifications/email
Authorization: Bearer {token}
Content-Type: application/json

{
  "to": "user@example.com",
  "subject": "Thông báo đơn hàng",
  "template": "ORDER_CONFIRMATION",
  "data": {
    "orderId": 123,
    "customerName": "Nguyễn Văn A"
  }
}
```

#### Gửi SMS
```http
POST /api/notifications/sms
Authorization: Bearer {token}
Content-Type: application/json

{
  "phoneNumber": "+84901234567",
  "message": "Đơn hàng #123 đã được xác nhận",
  "template": "ORDER_SMS"
}
```

#### Gửi push notification
```http
POST /api/notifications/push
Authorization: Bearer {token}
Content-Type: application/json

{
  "userId": 456,
  "title": "Đơn hàng mới",
  "body": "Bạn có đơn hàng mới cần xử lý",
  "data": {
    "orderId": 123,
    "type": "NEW_ORDER"
  }
}
```

#### Lấy lịch sử thông báo
```http
GET /api/notifications/history
Authorization: Bearer {token}
```

#### Tạo template thông báo
```http
POST /api/notifications/templates
Authorization: Bearer {token}
Content-Type: application/json

{
  "name": "ORDER_CONFIRMATION",
  "type": "EMAIL",
  "subject": "Xác nhận đơn hàng #{orderId}",
  "content": "Xin chào {customerName}, đơn hàng #{orderId} đã được xác nhận."
}
```

## 🧪 Testing

### Chạy unit tests cho tất cả services
```bash
# Chạy tests cho tất cả services
./gradlew test

# Chạy tests cho từng service riêng lẻ
./gradlew :api-gateway:test
./gradlew :product-service:test
./gradlew :user-service:test
./gradlew :cart-service:test
./gradlew :order-service:test
./gradlew :payment-service:test
./gradlew :notification-service:test
```

### Chạy integration tests
```bash
# Chạy integration tests cho tất cả services
./gradlew integrationTest

# Chạy integration tests cho từng service
./gradlew :api-gateway:integrationTest
./gradlew :product-service:integrationTest
./gradlew :user-service:integrationTest
./gradlew :cart-service:integrationTest
./gradlew :order-service:integrationTest
./gradlew :payment-service:integrationTest
./gradlew :notification-service:integrationTest
```

## 🐳 Docker

### Infrastructure Services
Docker Compose bao gồm các infrastructure services:
- **PostgreSQL 15**: Database với multiple databases cho từng service
- **Kafka + Zookeeper**: Message broker cho event-driven architecture
- **Redis 7**: Caching và session management
- **Prometheus**: Metrics collection
- **Grafana**: Monitoring dashboard
- **Kafka UI**: Web interface cho Kafka management

### Build Docker images cho tất cả services
```bash
# Build tất cả services
docker-compose build

# Build từng service riêng lẻ
docker build -t grocery-api-gateway:latest ./api-gateway
docker build -t grocery-product-service:latest ./product-service
docker build -t grocery-user-service:latest ./user-service
docker build -t grocery-cart-service:latest ./cart-service
docker build -t grocery-order-service:latest ./order-service
docker build -t grocery-payment-service:latest ./payment-service
docker build -t grocery-notification-service:latest ./notification-service
```

### Run với Docker Compose

#### Phiên bản đơn giản
```bash
# Chạy tất cả services
docker-compose -f docker-compose.simple.yml up -d

# Chạy với logs
docker-compose -f docker-compose.simple.yml up

# Chạy từng service riêng lẻ
docker-compose -f docker-compose.simple.yml up -d api-gateway
docker-compose -f docker-compose.simple.yml up -d product-service
```

#### Phiên bản đầy đủ
```bash
# Chạy tất cả services
docker-compose up -d

# Chạy với logs
docker-compose up

# Chạy từng service riêng lẻ
docker-compose up -d api-gateway
docker-compose up -d product-service
docker-compose up -d user-service
docker-compose up -d cart-service
docker-compose up -d order-service
docker-compose up -d payment-service
docker-compose up -d notification-service
```

### Stop services

#### Phiên bản đơn giản
```bash
# Stop tất cả services
docker-compose -f docker-compose.simple.yml down

# Stop và xóa volumes
docker-compose -f docker-compose.simple.yml down -v
```

#### Phiên bản đầy đủ
```bash
# Stop tất cả services
docker-compose down

# Stop và xóa volumes
docker-compose down -v

# Stop từng service
docker-compose stop api-gateway
docker-compose stop product-service
docker-compose stop user-service
```

## 📈 Monitoring & Health Checks

### Health Checks cho tất cả services
- **API Gateway**: `http://localhost:8080/actuator/health`
- **Product Service**: `http://localhost:8081/actuator/health`
- **User Service**: `http://localhost:8082/actuator/health`
- **Cart Service**: `http://localhost:8083/actuator/health`
- **Order Service**: `http://localhost:8084/actuator/health`
- **Payment Service**: `http://localhost:8085/actuator/health`
- **Notification Service**: `http://localhost:8086/actuator/health`

### Metrics và Monitoring
- **Metrics**: `/actuator/metrics`
- **Info**: `/actuator/info`
- **Prometheus**: `/actuator/prometheus` (nếu có cấu hình)

## 🔄 Service Communication Flow

```
Client Request → API Gateway (Port: 8080) → Microservices
                    ↓
              ┌─────────────────────────────────────────┐
              │  API Gateway Functions:                 │
              │  • Authentication & Authorization       │
              │  • Request Routing & Load Balancing     │
              │  • Rate Limiting & Throttling          │
              │  • Circuit Breaker & Retry Logic       │
              │  • Request/Response Logging            │
              │  • CORS Handling                       │
              └─────────────────────────────────────────┘
                    ↓
              ┌─────────────────────────────────────────┐
              │  Microservices:                        │
              │  • User Service (8082) - Auth          │
              │  • Product Service (8081) - Products   │
              │  • Cart Service (8083) - Shopping Cart │
              │  • Order Service (8084) - Orders       │
              │  • Payment Service (8085) - Payments   │
              │  • Notification Service (8086) - Notify│
              └─────────────────────────────────────────┘
```

### Request Flow Example:
```
1. Client → API Gateway (8080) → User Service (8082) - Login
2. Client → API Gateway (8080) → Product Service (8081) - Browse Products
3. Client → API Gateway (8080) → Cart Service (8083) - Add to Cart
4. Client → API Gateway (8080) → Order Service (8084) - Create Order
5. Client → API Gateway (8080) → Payment Service (8085) - Process Payment
6. Order Service → Notification Service (8086) - Send Order Confirmation
```

## 🗄️ Database Schema

### Product Service Database
- **Products Table**: Thông tin sản phẩm
- **Categories Table**: Danh mục sản phẩm
- **Inventory Table**: Quản lý tồn kho

### User Service Database
- **Users Table**: Thông tin người dùng
- **Addresses Table**: Địa chỉ giao hàng
- **User_Roles Table**: Phân quyền người dùng

### Cart Service Database
- **Cart Table**: Giỏ hàng
- **Cart_Items Table**: Sản phẩm trong giỏ hàng

### Order Service Database
- **Orders Table**: Đơn hàng
- **Order_Items Table**: Sản phẩm trong đơn hàng
- **Order_Status Table**: Trạng thái đơn hàng

### Payment Service Database
- **Payments Table**: Thông tin thanh toán
- **Payment_Methods Table**: Phương thức thanh toán
- **Transactions Table**: Lịch sử giao dịch

### Notification Service Database
- **Notifications Table**: Thông tin thông báo
- **Notification_Templates Table**: Template thông báo
- **Notification_History Table**: Lịch sử gửi thông báo
- **User_Notification_Settings Table**: Cài đặt thông báo của người dùng

## 🤝 Đóng góp

1. Fork repository
2. Tạo feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Tạo Pull Request

## 📝 License

Dự án này được phát triển cho mục đích học tập tại Đại học Bách Khoa TP.HCM.

## 👥 Tác giả

- **Sinh viên**: VNU HCMUT
- **Môn học**: [Tên môn học]
- **Giảng viên**: [Tên giảng viên]

## 📞 Liên hệ

- Email: [vinhnh.2312@gmail.com]
- GitHub: [hoaqdzink]

---

## 📖 Tài liệu bổ sung

- **[Environment Setup Guide](ENVIRONMENT_SETUP.md)**: Hướng dẫn chi tiết cấu hình biến môi trường
- **[Docker Compose Simple](docker-compose.simple.yml)**: Cấu hình đơn giản (PostgreSQL + Redis)
- **[Docker Compose Full](docker-compose.yml)**: Cấu hình đầy đủ với PostgreSQL, Kafka, Redis, Monitoring
- **[Environment Properties](environment.properties)**: Template cho các biến môi trường

---

**Lưu ý**: Đây là dự án học tập, không sử dụng cho mục đích thương mại.
