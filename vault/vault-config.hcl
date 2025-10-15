# ==================== VAULT CONFIGURATION ====================
# File cấu hình Vault cho Grocery Project
# Chạy trong development mode với in-memory storage

storage "inmem" {}

listener "tcp" {
  address = "0.0.0.0:8200"
  tls_disable = 1
}

disable_mlock = true

# Development mode - KHÔNG sử dụng trong production
disable_cache = true
disable_mlock = true

# UI configuration
ui = true

# Logging
log_level = "INFO"
log_format = "json"

# API configuration
api_addr = "http://0.0.0.0:8200"
cluster_addr = "http://0.0.0.0:8201"

# Default lease TTL
default_lease_ttl = "168h"  # 7 days
max_lease_ttl = "720h"      # 30 days