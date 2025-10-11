# 🗄️ Database Schema Documentation

Thư mục chứa database schema cho hệ thống Grocery Store.

## 📁 Files

- **`database.md`** - Database schema sử dụng DBML (Database Markup Language)

## 🎨 Extensions khuyến nghị

### 1. **DBML Extension (Khuyến nghị)**
- **Extension ID**: `holistics.dbml`
- **Tính năng**:
  - ✅ Syntax highlighting cho DBML
  - ✅ Live preview diagram
  - ✅ Export sang PNG, PDF, SQL
  - ✅ Auto-completion và validation

### 2. **Cách cài đặt:**
```bash
# Trong VS Code
Ctrl+Shift+X → Search "DBML" → Install
```

### 3. **Cách sử dụng:**
```bash
# Preview diagram
Ctrl+Shift+P → "DBML: Preview"

# Export diagram
Ctrl+Shift+P → "DBML: Export to PNG"
Ctrl+Shift+P → "DBML: Export to PDF"
Ctrl+Shift+P → "DBML: Export to SQL"
```

## 🏗️ Database Schema Overview

### **📊 Tables chính:**

#### **👤 User Management:**
- `User` - Thông tin người dùng
- `Address` - Địa chỉ người dùng
- `Role` - Vai trò người dùng

#### **🛍️ Product Management:**
- `Category` - Danh mục sản phẩm
- `Branch` - Thương hiệu
- `Product` - Sản phẩm
- `ProductSize` - Kích thước sản phẩm
- `ProductImage` - Hình ảnh sản phẩm
- `Inventory` - Kho hàng

#### **🛒 Order Management:**
- `Order` - Đơn hàng
- `OrderDetail` - Chi tiết đơn hàng
- `OrderHistory` - Lịch sử đơn hàng
- `Shipping` - Vận chuyển
- `Payment` - Thanh toán

#### **🎫 Promotion:**
- `Coupon` - Mã giảm giá
- `CouponUsage` - Sử dụng mã giảm giá

#### **📢 Communication:**
- `Notification` - Thông báo
- `FeedBack` - Phản hồi
- `FeedMessage` - Tin nhắn phản hồi

#### **🧾 Invoice:**
- `Invoice` - Hóa đơn

## 🔗 Relationships

### **One-to-Many:**
- `User` → `Address` (1 user có nhiều địa chỉ)
- `User` → `Order` (1 user có nhiều đơn hàng)
- `Category` → `Product` (1 category có nhiều sản phẩm)
- `Branch` → `Product` (1 brand có nhiều sản phẩm)
- `Product` → `ProductSize` (1 product có nhiều size)
- `ProductSize` → `Inventory` (1 product size có 1 inventory)
- `ProductSize` → `ProductImage` (1 product size có nhiều hình ảnh)
- `Order` → `OrderDetail` (1 order có nhiều order detail)
- `Order` → `OrderHistory` (1 order có nhiều history)
- `Order` → `Shipping` (1 order có 1 shipping)
- `Order` → `Payment` (1 order có 1 payment)
- `Order` → `Invoice` (1 order có 1 invoice)
- `Coupon` → `CouponUsage` (1 coupon có nhiều usage)
- `User` → `Notification` (1 user có nhiều notification)
- `User` → `FeedBack` (1 user có nhiều feedback)
- `FeedBack` → `FeedMessage` (1 feedback có nhiều message)

## 🎯 Key Features

### **✅ Đã implement:**
- ✅ User management với roles
- ✅ Product catalog với categories và brands
- ✅ Inventory management
- ✅ Order processing
- ✅ Payment integration
- ✅ Shipping tracking
- ✅ Coupon system
- ✅ Notification system
- ✅ Feedback system
- ✅ Invoice generation

### **🔧 Cải tiến đã thêm:**
- ✅ **Data types** chi tiết (varchar length, constraints)
- ✅ **Constraints** (not null, unique, default values)
- ✅ **Timestamps** (createdAt, updatedAt)
- ✅ **Soft delete** (isActive flags)
- ✅ **Comments** và notes cho các fields
- ✅ **Proper naming** convention

## 🚀 Cách sử dụng

### **1. Xem schema:**
```bash
# Mở file database.md trong VS Code với DBML extension
# Sử dụng Ctrl+Shift+P → "DBML: Preview"
```

### **2. Export SQL:**
```bash
# Export sang SQL để tạo database
Ctrl+Shift+P → "DBML: Export to SQL"
```

### **3. Generate diagram:**
```bash
# Export diagram để documentation
Ctrl+Shift+P → "DBML: Export to PNG"
```

## 📚 Tài liệu tham khảo

- [DBML Documentation](https://dbml.dbdiagram.io/docs/)
- [DBML Syntax Guide](https://dbml.dbdiagram.io/docs/#syntax)
- [VS Code DBML Extension](https://marketplace.visualstudio.com/items?itemName=holistics.dbml)

## 🎨 Preview

Sau khi cài đặt DBML extension, bạn sẽ thấy:
- 🎨 **Syntax highlighting** đẹp mắt
- 📊 **Live preview** diagram
- 🔍 **Auto-completion** khi typing
- ✅ **Error detection** và validation
- 📤 **Export** sang nhiều format
