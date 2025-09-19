package com.ecom.productservice.controller;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.productservice.dto.CategoriesDTO;
import com.ecom.productservice.dto.CategoriesDTO.CategoryDTO;
import com.ecom.productservice.entity.Category;
import com.ecom.productservice.dto.ProductItemsDTO;
import com.ecom.productservice.dto.ProductsDTO;
import com.ecom.productservice.dto.VariantsDTO;
import com.ecom.productservice.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	

	@GetMapping("/getproducts")
	private ResponseEntity<CategoriesDTO> getProducts() {

		return ResponseEntity.ok(productService.getAllProducts());
	}

	@PostMapping("/newproduct")
	private ResponseEntity<CategoriesDTO.CategoryDTO> addProducts(@RequestBody CategoriesDTO.CategoryDTO category ) {

		return ResponseEntity.ok(productService.saveCategories(category));

	}
	
	@GetMapping("/{id}")
	private ResponseEntity<CategoriesDTO.CategoryDTO> findProductById(@PathVariable("id") UUID id){
		
		return ResponseEntity.ok(productService.findById(id));
		
	}
	
	@PutMapping("/updateproduct")
	private ResponseEntity<CategoriesDTO.CategoryDTO> updateProducts(@RequestBody CategoriesDTO.CategoryDTO category ) {

		return ResponseEntity.ok(productService.saveCategories(category));

	}

	private CategoriesDTO getCategoires() {
		List<CategoryDTO> list = new ArrayList<CategoriesDTO.CategoryDTO>();
		List<VariantsDTO> varList = new ArrayList<VariantsDTO>();
		List<ProductItemsDTO> itemList = new ArrayList<ProductItemsDTO>();
		List<ProductsDTO> prodList1 = new ArrayList<ProductsDTO>();
		List<ProductsDTO> prodList2 = new ArrayList<ProductsDTO>();
		prodList1.add(ProductsDTO.builder().id(UUID.randomUUID()).available(true).brand("Polo")
				.description("PoloTshits will fit good").createdOn(OffsetDateTime.now()).createdBy("Admin").price(new BigDecimal(1000)).build());
		prodList1.add(ProductsDTO.builder().id(UUID.randomUUID()).available(true).brand("Jockey")
				.description("Jockey Tshits will fit good").createdOn(OffsetDateTime.now()).createdBy("Admin").price(new BigDecimal(2000)).build());
		prodList2.add(ProductsDTO.builder().id(UUID.randomUUID()).available(true).brand("Peter England")
				.description("Formal PE will fit good").createdOn(OffsetDateTime.now()).createdBy("Admin").price(new BigDecimal(400)).build());
		prodList2.add(ProductsDTO.builder().id(UUID.randomUUID()).available(true).brand("Bombay Dying")
				.description("Formal Bombay Dying will fit good").createdOn(OffsetDateTime.now()).createdBy("Admin").price(new BigDecimal(1000))
				.build());

		itemList.add(ProductItemsDTO.builder().id(UUID.randomUUID()).available(true).productItemName("Formal Shirts")
				.createdOn(OffsetDateTime.now()).createdBy("Admin").products(prodList2).build());
		itemList.add(ProductItemsDTO.builder().id(UUID.randomUUID()).available(true).productItemName("T Shirts")
				.createdOn(OffsetDateTime.now()).createdBy("Admin").products(prodList1).build());
		varList.add(VariantsDTO.builder().id(UUID.randomUUID()).available(true).createdOn(OffsetDateTime.now())
				.createdBy("Admin").variantName("Men's Clothing").productItems(itemList).build());
		list.add(CategoriesDTO.CategoryDTO.builder().id(UUID.randomUUID()).categoryName("Fashions").available(true)
				.createdOn(OffsetDateTime.now()).createdBy("Admin").variants(varList).build());

		return CategoriesDTO.builder().categories(list).build();

	}

}
