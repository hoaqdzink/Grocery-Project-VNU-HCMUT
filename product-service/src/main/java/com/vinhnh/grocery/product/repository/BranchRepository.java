package com.vinhnh.grocery.product.repository;

import org.springframework.stereotype.Repository;

import com.vinhnh.grocery.product.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

@Repository
public interface BranchRepository extends JpaRepository<Branch, UUID> {
    
}
