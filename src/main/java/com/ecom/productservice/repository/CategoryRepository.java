package com.ecom.productservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.productservice.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
	
	
}
