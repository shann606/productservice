package com.ecom.productservice.service;

import static com.ecom.productservice.util.CategorySpecfication.filterBy;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.productservice.datamapper.CustomMapper;
import com.ecom.productservice.dto.CategoriesDTO;
import com.ecom.productservice.dto.CategoriesDTO.CategoryDTO;
import com.ecom.productservice.dto.ProductsDTO;
import com.ecom.productservice.dto.VariantsDTO;
import com.ecom.productservice.entity.Category;
import com.ecom.productservice.entity.Products;
import com.ecom.productservice.entity.Variants;
import com.ecom.productservice.repository.CategoryRepository;
import com.ecom.productservice.repository.ProductsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	private CategoryRepository catRepo;

	private CustomMapper mapper;

	private ProductsRepository prodRepo;

	public ProductService(CategoryRepository catRepo, CustomMapper mapper, ProductsRepository prodRepo) {
		this.catRepo = catRepo;
		this.mapper = mapper;
		this.prodRepo = prodRepo;
	}

	public CategoriesDTO.CategoryDTO saveCategories(CategoriesDTO.CategoryDTO category) {
		Category categoryE = catRepo.saveAndFlush(mapper.toCategoryEntity(category));

		return mapper.toCategoryDTO(categoryE);
	}

	public CategoriesDTO getAllProducts() {

		List<CategoryDTO> list = mapper.toListCategoryDTO(catRepo.findAll(Sort.by("categoryName").descending()));

		return CategoriesDTO.builder().categories(list).build();
	}

	public CategoriesDTO.CategoryDTO findById(UUID id) {

		Category cat = catRepo.findById(id).orElseThrow();

		return mapper.toCategoryDTO(cat);
	}

	public List<ProductsDTO> getRecommendedProducts(UUID prodItemId) {

		return mapper.toProductsDTO(prodRepo.findAllByProductItemId(prodItemId));
	}

	public CategoriesDTO filterCategoryBy(String catName, boolean available, String createdBy) {

		List<Category> list = catRepo.findAll(filterBy(catName, available, createdBy));

		return CategoriesDTO.builder().categories(mapper.toListCategoryDTO(list)).build();
	}

	public VariantsDTO findVariants(UUID id, UUID variantId) {

		Variants variants = catRepo.findById(id).orElseThrow().getVariants().stream()
				.filter(x -> x.getId().equals(variantId)).findAny().orElseThrow();

		return mapper.toVariantsDTO(variants);
	}

	@Transactional(rollbackFor = Exception.class)
	public String updateQuantity(UUID id, int quantity) {

		Products product = prodRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

		product.setQuantity(quantity);
		return "Updated Successfully";
	}

	public String checkQuantity(UUID id, int quantity) {
		return prodRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"))
				.getQuantity() < quantity ? "not available" : "available";
	}

}
