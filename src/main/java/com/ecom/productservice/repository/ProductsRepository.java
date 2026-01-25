package com.ecom.productservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.stereotype.Repository;

import com.ecom.productservice.entity.Products;

import jakarta.persistence.LockModeType;

@Repository
public interface ProductsRepository extends JpaRepository<Products, UUID> {
	
	@Meta(comment = "Finding All the products by passing product item id")
	@Lock(LockModeType.READ)
	List<Products> findAllByProductItemId(UUID productItemID);

}
