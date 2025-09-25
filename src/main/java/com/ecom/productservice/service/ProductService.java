package com.ecom.productservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.productservice.datamapper.CustomMapper;
import com.ecom.productservice.dto.CategoriesDTO;
import com.ecom.productservice.dto.CategoriesDTO.CategoryDTO;
import com.ecom.productservice.dto.ProductsDTO;
import com.ecom.productservice.entity.Category;
import com.ecom.productservice.entity.Products;
import com.ecom.productservice.repository.CategoryRepository;
import com.ecom.productservice.repository.ProductsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private CustomMapper mapper;
	
	@Autowired
	private ProductsRepository prodRepo;

	public CategoriesDTO.CategoryDTO saveCategories(CategoriesDTO.CategoryDTO category) {
		Category categoryE = catRepo.saveAndFlush(mapper.toCategoryEntity(category));
		
		return mapper.toCategoryDTO(categoryE);
	}

	public CategoriesDTO getAllProducts() {

		List<CategoryDTO> list = mapper.toListCategoryDTO(catRepo.findAll());

		return CategoriesDTO.builder().categories(list).build();
	}

	public CategoriesDTO.CategoryDTO  findById(UUID id) {
		// TODO Auto-generated method stub
		Optional<Category> opt=catRepo.findById(id);
		return mapper.toCategoryDTO(opt.get());
	}

	public List<ProductsDTO> getRecommendedProducts(UUID prodItemId) throws Exception{
		// TODO Auto-generated method stub
		return  mapper.toProductsDTO(prodRepo.findAllByProductItemId(prodItemId));
	}

}
