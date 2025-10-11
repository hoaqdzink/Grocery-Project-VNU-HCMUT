#!/bin/bash

# Test script để kiểm tra CategoryController API
echo "🧪 Testing CategoryController API endpoints..."
echo "=============================================="

# Base URL
BASE_URL="http://localhost:8081"

echo "📋 1. Testing GET /api/v1/categories (Get all categories with pagination)"
echo "Command: curl -X GET $BASE_URL/api/v1/categories"
echo "Response:"
curl -X GET "$BASE_URL/api/v1/categories" \
  -H "Accept: application/json" \
  -w "\nHTTP Status: %{http_code}\n" \
  -s || echo "❌ Connection failed - Application might not be running"

echo ""
echo "📋 2. Testing GET /api/v1/categories/active (Get active categories)"
echo "Command: curl -X GET $BASE_URL/api/v1/categories/active"
echo "Response:"
curl -X GET "$BASE_URL/api/v1/categories/active" \
  -H "Accept: application/json" \
  -w "\nHTTP Status: %{http_code}\n" \
  -s || echo "❌ Connection failed - Application might not be running"

echo ""
echo "📋 3. Testing POST /api/v1/categories (Create new category)"
echo "Command: curl -X POST $BASE_URL/api/v1/categories"
echo "Response:"
curl -X POST "$BASE_URL/api/v1/categories" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "name": "Test Category",
    "description": "This is a test category created by API test script"
  }' \
  -w "\nHTTP Status: %{http_code}\n" \
  -s || echo "❌ Connection failed - Application might not be running"

echo ""
echo "📋 4. Testing GET /api/v1/categories/count/active (Count active categories)"
echo "Command: curl -X GET $BASE_URL/api/v1/categories/count/active"
echo "Response:"
curl -X GET "$BASE_URL/api/v1/categories/count/active" \
  -H "Accept: application/json" \
  -w "\nHTTP Status: %{http_code}\n" \
  -s || echo "❌ Connection failed - Application might not be running"

echo ""
echo "📋 5. Testing Swagger UI availability"
echo "Command: curl -X GET $BASE_URL/swagger-ui.html"
echo "Response:"
curl -X GET "$BASE_URL/swagger-ui.html" \
  -w "\nHTTP Status: %{http_code}\n" \
  -s | head -5 || echo "❌ Swagger UI not accessible"

echo ""
echo "=============================================="
echo "✅ Test completed!"
echo ""
echo "📝 How to interpret results:"
echo "   - HTTP 200: ✅ Endpoint working correctly"
echo "   - HTTP 404: ❌ Endpoint not found (Controller not registered)"
echo "   - HTTP 500: ⚠️  Server error (Check application logs)"
echo "   - Connection failed: ❌ Application not running on port 8081"
echo ""
echo "🚀 To start the application:"
echo "   cd product-service && ./gradlew bootRun"
