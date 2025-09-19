package com.ecom.productservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ecom.productservice.datamapper.CustomMapper;
import com.ecom.productservice.dto.CategoriesDTO;
import com.ecom.productservice.dto.CategoriesDTO.CategoryDTO;
import com.ecom.productservice.entity.Category;
import com.ecom.productservice.entity.ProductItems;
import com.ecom.productservice.entity.Products;
import com.ecom.productservice.entity.Variants;
import com.ecom.productservice.repository.CategoryRepository;

@ExtendWith({ MockitoExtension.class })
@SpringBootTest
class ProductServiceTest {

	@Mock
	private CategoryRepository catRepo;
	@Spy
	private CustomMapper mapper = Mappers.getMapper(CustomMapper.class);

	@InjectMocks
	private ProductService productSerivce;

	private static CategoriesDTO.CategoryDTO category;

	private static Category catE;

	@BeforeAll
	static void setData() {

		List<CategoryDTO> list = new ArrayList<CategoriesDTO.CategoryDTO>();
		List<Variants> varList = new ArrayList<Variants>();
		List<ProductItems> itemList = new ArrayList<ProductItems>();
		List<Products> prodList1 = new ArrayList<Products>();
		List<Products> prodList2 = new ArrayList<Products>();
		prodList1.add(Products.builder().id(UUID.randomUUID()).available(true).brand("Polo")
				.createdOn(OffsetDateTime.now()).createdBy("Admin").price(new BigDecimal(1000)).build());
		prodList1.add(Products.builder().id(UUID.randomUUID()).available(true).brand("Jockey")
				.createdOn(OffsetDateTime.now()).createdBy("Admin").price(new BigDecimal(2000)).build());
		prodList2.add(Products.builder().id(UUID.randomUUID()).available(true).brand("Peter England")
				.createdOn(OffsetDateTime.now()).createdBy("Admin").price(new BigDecimal(400)).build());
		prodList2.add(Products.builder().id(UUID.randomUUID()).available(true).brand("Bombay Dying")
				.createdOn(OffsetDateTime.now()).createdBy("Admin").price(new BigDecimal(1000)).build());

		itemList.add(ProductItems.builder().id(UUID.randomUUID()).available(true).productItemName("Formal Shirts")
				.createdOn(OffsetDateTime.now()).createdBy("Admin").products(prodList2).build());
		itemList.add(ProductItems.builder().id(UUID.randomUUID()).available(true).productItemName("T Shirts")
				.createdOn(OffsetDateTime.now()).createdBy("Admin").products(prodList1).build());
		varList.add(Variants.builder().id(UUID.randomUUID()).available(true).createdOn(OffsetDateTime.now())
				.createdBy("Admin").variantName("Men's Clothing").productItems(itemList).build());

		catE = catE.builder().id(UUID.fromString("b516f577-11da-424e-9ad0-bc23ab15df1b")).categoryName("Fashions")
				.available(true).createdOn(OffsetDateTime.now()).createdBy("Admin").variants(varList).build();

	}

	@BeforeEach
	void setStub() {
		lenient().when(catRepo.saveAndFlush(catE)).thenReturn(catE);
	}

	@BeforeEach
	void getIdStud() {
		lenient().when(catRepo.findById(UUID.fromString("b516f577-11da-424e-9ad0-bc23ab15df1b")))
				.thenReturn(Optional.of(catE));
	}

	@Test
	void testSaveCategories() {
		CategoriesDTO.CategoryDTO c = mapper.toCategoryDTO(catE);
		CategoriesDTO.CategoryDTO result = productSerivce.saveCategories(c);
		verify(catRepo, atLeastOnce()).saveAndFlush(catE);

	}

	@Test
	void testFindById() {

		CategoriesDTO.CategoryDTO c = productSerivce
				.findById((UUID.fromString("b516f577-11da-424e-9ad0-bc23ab15df1b")));
		verify(catRepo, atLeastOnce()).findById((UUID.fromString("b516f577-11da-424e-9ad0-bc23ab15df1b")));
	}

}
