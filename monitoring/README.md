# 📊 Monitoring Configuration

Thư mục chứa cấu hình monitoring cho hệ thống Grocery Store.

## 📁 Cấu trúc thư mục

```
monitoring/
├── prometheus.yml                    # Cấu hình Prometheus
├── grafana/
│   ├── datasources/
│   │   └── prometheus.yml           # Cấu hình datasource Prometheus cho Grafana
│   └── dashboards/
│       └── dashboard.yml            # Cấu hình dashboard provider cho Grafana
└── README.md                        # File này
```

## 🔧 Các file cấu hình

### 1. **prometheus.yml**
- **Mục đích**: Cấu hình Prometheus để thu thập metrics từ các services
- **Chức năng chính**:
  - Thu thập metrics từ 6 microservices (API Gateway, Product, User, Cart, Order, Payment, Notification)
  - Thu thập metrics từ 6 PostgreSQL databases
  - Thu thập metrics từ Redis và Kafka
  - Tự monitor chính Prometheus

### 2. **grafana/datasources/prometheus.yml**
- **Mục đích**: Cấu hình kết nối Prometheus làm datasource cho Grafana
- **Chức năng**: Cho phép Grafana lấy dữ liệu metrics từ Prometheus để tạo dashboard

### 3. **grafana/dashboards/dashboard.yml**
- **Mục đích**: Cấu hình provider để tự động load dashboards
- **Chức năng**: Tự động load các dashboard files từ thư mục được chỉ định

## 🚀 Cách hoạt động

### **Luồng dữ liệu monitoring:**
```
Microservices → Spring Boot Actuator → Prometheus → Grafana → Dashboard
     ↓              ↓                    ↓           ↓
  Metrics      /actuator/prometheus   Thu thập   Hiển thị
```

### **1. Thu thập Metrics**
- **Spring Boot Actuator**: Mỗi microservice expose metrics qua endpoint `/actuator/prometheus`
- **Prometheus**: Thu thập metrics từ các services theo cấu hình trong `prometheus.yml`
- **Tần suất**: 
  - Application services: 10 giây
  - Database services: 30 giây

### **2. Lưu trữ và Query**
- **Prometheus**: Lưu trữ metrics trong time-series database
- **PromQL**: Ngôn ngữ query để lấy dữ liệu metrics

### **3. Visualization**
- **Grafana**: Kết nối đến Prometheus qua datasource
- **Dashboard**: Hiển thị metrics dưới dạng biểu đồ, bảng, alert

## 📊 Metrics được thu thập

### **Application Metrics (Spring Boot Actuator)**
- **JVM Metrics**: Memory, CPU, GC
- **HTTP Metrics**: Request count, response time, error rate
- **Database Metrics**: Connection pool, query time
- **Custom Metrics**: Business logic metrics

### **Database Metrics (PostgreSQL)**
- **Connection Metrics**: Active connections, max connections
- **Query Metrics**: Query count, slow queries
- **Storage Metrics**: Database size, table size

### **Infrastructure Metrics**
- **Redis**: Memory usage, hit rate, operations per second
- **Kafka**: Message rate, consumer lag, partition count

## 🔍 Cách sử dụng

### **1. Truy cập Prometheus**
```bash
# Mở trình duyệt
http://localhost:9090

# Query metrics
up{job="product-service"}  # Kiểm tra service có hoạt động không
http_requests_total        # Tổng số HTTP requests
jvm_memory_used_bytes      # Memory usage của JVM
```

### **2. Truy cập Grafana**
```bash
# Mở trình duyệt
http://localhost:3000

# Đăng nhập
Username: admin
Password: admin (hoặc từ biến môi trường GRAFANA_ADMIN_PASSWORD)
```

### **3. Tạo Dashboard**
1. Vào **Dashboards** → **New Dashboard**
2. Chọn **Add Panel**
3. Chọn datasource **Prometheus**
4. Viết PromQL query
5. Chọn visualization type (Graph, Table, Stat, etc.)

## 📈 Ví dụ PromQL Queries

### **Service Health**
```promql
# Kiểm tra tất cả services có hoạt động không
up

# Kiểm tra Product Service
up{job="product-service"}

# Kiểm tra tất cả databases
up{job=~"postgres-.*"}
```

### **HTTP Metrics**
```promql
# Tổng số requests
http_requests_total

# Response time trung bình
http_request_duration_seconds{quantile="0.5"}

# Error rate
rate(http_requests_total{status=~"5.."}[5m])
```

### **JVM Metrics**
```promql
# Memory usage
jvm_memory_used_bytes{area="heap"}

# GC time
jvm_gc_pause_seconds

# Thread count
jvm_threads_live
```

### **Database Metrics**
```promql
# Active connections
pg_stat_activity_count

# Database size
pg_database_size_bytes
```

## 🚨 Alerting (Tương lai)

Có thể thêm alerting rules trong `prometheus.yml`:

```yaml
rule_files:
  - "alerts.yml"  # File chứa alerting rules
```

Ví dụ alerting rules:
```yaml
groups:
  - name: grocery-alerts
    rules:
      - alert: ServiceDown
        expr: up == 0
        for: 1m
        labels:
          severity: critical
        annotations:
          summary: "Service {{ $labels.job }} is down"
```

## 🔧 Troubleshooting

### **Prometheus không thu thập được metrics**
1. Kiểm tra service có expose `/actuator/prometheus` không
2. Kiểm tra network connectivity giữa Prometheus và services
3. Kiểm tra logs: `docker-compose logs prometheus`

### **Grafana không kết nối được Prometheus**
1. Kiểm tra Prometheus có chạy không: `docker-compose ps prometheus`
2. Kiểm tra URL trong datasource: `http://prometheus:9090`
3. Test connection trong Grafana UI

### **Dashboard không hiển thị dữ liệu**
1. Kiểm tra PromQL query có đúng không
2. Kiểm tra time range
3. Kiểm tra datasource có được chọn đúng không

## 📚 Tài liệu tham khảo

- [Prometheus Documentation](https://prometheus.io/docs/)
- [Grafana Documentation](https://grafana.com/docs/)
- [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
- [PromQL Tutorial](https://prometheus.io/docs/prometheus/latest/querying/basics/)
