package com.vinhnh.grocery.product.repository;

import org.springframework.stereotype.Repository;

import com.vinhnh.grocery.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    
}
