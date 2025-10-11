#!/bin/bash

echo "🛑 Stopping Grocery Store..."

# Dừng Java processes
pkill -f "grocery" 2>/dev/null || true

# Dừng theo ports
for port in 8080 8081 8082 8083 8084 8085 8086; do
    lsof -ti:$port | xargs kill -9 2>/dev/null || true
done

echo "✅ Stopped!"
