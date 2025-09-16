package com.ecom.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.productservice.entity.Category;
import com.ecom.productservice.repository.CategoryRepository;

@Service
public class ProductService {
	
	@Autowired
	private CategoryRepository catRepo;
	
	public void saveCategories(Category category)
	{
		 catRepo.save(category);
	}
	
	

}
