#!/bin/bash

echo "🚀 Starting Grocery Store..."

# Dừng processes cũ
echo "🛑 Stopping existing processes..."
pkill -f "grocery" 2>/dev/null || true
for port in 8080 8081 8082 8083 8084 8085 8086; do
    lsof -ti:$port | xargs kill -9 2>/dev/null || true
done

# Clean build trước khi start
echo "🧹 Cleaning and building project..."
./gradlew clean build -x test

# Load .env và chạy services
echo "📋 Loading .env and starting services..."
source .env && ./gradlew bootRun --exclude-task common:bootRun
