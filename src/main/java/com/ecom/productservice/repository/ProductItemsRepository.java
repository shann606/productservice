package com.ecom.productservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.productservice.entity.ProductItems;

@Repository
public interface ProductItemsRepository extends JpaRepository<ProductItems, UUID> {

}
