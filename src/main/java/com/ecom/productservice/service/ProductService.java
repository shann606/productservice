package com.ecom.productservice.service;

import static com.ecom.productservice.util.CategorySpecfication.filterBy;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecom.productservice.datamapper.CustomMapper;
import com.ecom.productservice.dto.CategoriesDTO;
import com.ecom.productservice.dto.CategoriesDTO.CategoryDTO;
import com.ecom.productservice.dto.ProductsDTO;
import com.ecom.productservice.entity.Category;
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

}
