#!/bin/bash

# ==================== VAULT SECRETS SETUP SCRIPT ====================
# Script để setup secrets trong Vault cho Grocery Project
# Chạy script này sau khi Vault container đã start

set -e

echo "🔐 Setting up Vault secrets for Grocery Project..."

# Vault configuration
VAULT_ADDR="http://localhost:8200"
VAULT_TOKEN="${VAULT_ROOT_TOKEN:-grocery-root-token}"

# Wait for Vault to be ready
echo "⏳ Waiting for Vault to be ready..."
until curl -s "$VAULT_ADDR/v1/sys/health" > /dev/null; do
  echo "Waiting for Vault..."
  sleep 2
done

echo "✅ Vault is ready!"

# Enable KV secrets engine
echo "🔧 Enabling KV secrets engine..."
curl -s \
  --header "X-Vault-Token: $VAULT_TOKEN" \
  --request POST \
  --data '{"type": "kv-v2"}' \
  "$VAULT_ADDR/v1/sys/mounts/secret"

# Setup secrets for each service
echo "📝 Setting up secrets for each service..."

# API Gateway secrets
curl -s \
  --header "X-Vault-Token: $VAULT_TOKEN" \
  --request POST \
  --data '{
    "data": {
      "jwt_secret": "grocery-jwt-secret-key-2024",
      "encryption_key": "grocery-encryption-key-32-chars",
      "api_key": "grocery-api-key-2024",
      "rate_limit": "1000"
    }
  }' \
  "$VAULT_ADDR/v1/secret/data/grocery/api-gateway"

# Product Service secrets
curl -s \
  --header "X-Vault-Token: $VAULT_TOKEN" \
  --request POST \
  --data '{
    "data": {
      "database_password": "product_db_password_2024",
      "cache_ttl": "3600",
      "image_upload_key": "product-image-upload-key"
    }
  }' \
  "$VAULT_ADDR/v1/secret/data/grocery/product-service"

# User Service secrets
curl -s \
  --header "X-Vault-Token: $VAULT_TOKEN" \
  --request POST \
  --data '{
    "data": {
      "database_password": "user_db_password_2024",
      "jwt_secret": "user-jwt-secret-2024",
      "password_salt": "user-password-salt-2024",
      "email_api_key": "email-api-key-placeholder"
    }
  }' \
  "$VAULT_ADDR/v1/secret/data/grocery/user-service"

# Cart Service secrets
curl -s \
  --header "X-Vault-Token: $VAULT_TOKEN" \
  --request POST \
  --data '{
    "data": {
      "database_password": "cart_db_password_2024",
      "session_timeout": "1800",
      "max_items": "100"
    }
  }' \
  "$VAULT_ADDR/v1/secret/data/grocery/cart-service"

# Order Service secrets
curl -s \
  --header "X-Vault-Token: $VAULT_TOKEN" \
  --request POST \
  --data '{
    "data": {
      "database_password": "order_db_password_2024",
      "order_timeout": "900",
      "notification_webhook": "https://api.example.com/order-webhook"
    }
  }' \
  "$VAULT_ADDR/v1/secret/data/grocery/order-service"

# Payment Service secrets
curl -s \
  --header "X-Vault-Token: $VAULT_TOKEN" \
  --request POST \
  --data '{
    "data": {
      "database_password": "payment_db_password_2024",
      "stripe_secret_key": "sk_test_stripe_secret_key_placeholder",
      "stripe_webhook_secret": "whsec_stripe_webhook_secret_placeholder",
      "vnpay_secret_key": "vnpay_secret_key_placeholder"
    }
  }' \
  "$VAULT_ADDR/v1/secret/data/grocery/payment-service"

# Notification Service secrets
curl -s \
  --header "X-Vault-Token: $VAULT_TOKEN" \
  --request POST \
  --data '{
    "data": {
      "database_password": "notification_db_password_2024",
      "smtp_password": "smtp_password_placeholder",
      "firebase_server_key": "firebase_server_key_placeholder",
      "twilio_auth_token": "twilio_auth_token_placeholder"
    }
  }' \
  "$VAULT_ADDR/v1/secret/data/grocery/notification-service"

# Shared secrets
curl -s \
  --header "X-Vault-Token: $VAULT_TOKEN" \
  --request POST \
  --data '{
    "data": {
      "redis_password": "redis_password_2024",
      "monitoring_api_key": "monitoring_api_key_2024",
      "external_api_key": "external_api_key_placeholder"
    }
  }' \
  "$VAULT_ADDR/v1/secret/data/grocery/shared"

echo "✅ All secrets have been set up successfully!"
echo ""
echo "🔍 You can verify the secrets by running:"
echo "   curl -H \"X-Vault-Token: $VAULT_TOKEN\" $VAULT_ADDR/v1/secret/data/grocery/api-gateway"
echo ""
echo "🌐 Access Vault UI at: http://localhost:8200"
echo "   Token: $VAULT_TOKEN"