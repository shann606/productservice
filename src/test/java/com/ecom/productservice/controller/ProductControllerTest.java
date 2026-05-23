package com.ecom.productservice.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ecom.productservice.dto.CategoriesDTO;
import com.ecom.productservice.dto.CategoriesDTO.CategoryDTO;
import com.ecom.productservice.dto.ProductItemsDTO;
import com.ecom.productservice.dto.ProductsDTO;
import com.ecom.productservice.dto.VariantsDTO;
import com.ecom.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockitoBean
	private ProductService productService;

	private static CategoriesDTO cat;
	private static CategoriesDTO.CategoryDTO category;
	private static List<ProductsDTO> recommendedProds;
	@Autowired
	private ObjectMapper objectMapper;

	@BeforeAll
	final static void init() {

		List<CategoryDTO> list = new ArrayList<CategoriesDTO.CategoryDTO>();
		List<VariantsDTO> varList = new ArrayList<VariantsDTO>();
		List<ProductItemsDTO> itemList = new ArrayList<ProductItemsDTO>();
		List<ProductsDTO> prodList1 = new ArrayList<ProductsDTO>();
		List<ProductsDTO> prodList2 = new ArrayList<ProductsDTO>();
		prodList1.add(ProductsDTO.builder().id(UUID.randomUUID()).available(true).quantity(4).brand("Polo")
				.description("PoloTshits will fit good").createdOn(OffsetDateTime.parse("2026-05-14T10:15:30+00:00"))
				.createdBy("Admin").price(new BigDecimal(1000)).build());
		prodList1.add(ProductsDTO.builder().id(UUID.randomUUID()).available(true).quantity(4).brand("Jockey")
				.description("Jockey Tshits will fit good").createdOn(OffsetDateTime.parse("2026-05-14T10:15:30+00:00"))
				.createdBy("Admin").price(new BigDecimal(2000)).build());
		prodList2.add(ProductsDTO.builder().id(UUID.randomUUID()).available(true).quantity(3).brand("Peter England")
				.description("Formal PE will fit good").createdOn(OffsetDateTime.parse("2026-05-14T10:15:30+00:00"))
				.createdBy("Admin").price(new BigDecimal(400)).build());
		prodList2.add(ProductsDTO.builder().id(UUID.randomUUID()).available(true).quantity(2).brand("Bombay Dying")
				.description("Formal Bombay Dying will fit good")
				.createdOn(OffsetDateTime.parse("2026-05-14T10:15:30+00:00")).createdBy("Admin")
				.price(new BigDecimal(1000)).build());

		itemList.add(
				ProductItemsDTO.builder().id(UUID.fromString("7fb2814b-b984-472b-bb22-ac69651c2859")).available(true)
						.productItemName("Formal Shirts").createdOn(OffsetDateTime.parse("2026-05-14T10:15:30+00:00"))
						.createdBy("Admin").products(prodList2).build());
		itemList.add(ProductItemsDTO.builder().id(UUID.randomUUID()).available(true).productItemName("T Shirts")
				.createdOn(OffsetDateTime.parse("2026-05-14T10:15:30+00:00")).createdBy("Admin").products(prodList1)
				.build());
		varList.add(VariantsDTO.builder().id(UUID.randomUUID()).available(true)
				.createdOn(OffsetDateTime.parse("2026-05-14T10:15:30+00:00")).createdBy("Admin")
				.variantName("Men's Clothing").productItems(itemList).build());
		list.add(CategoriesDTO.CategoryDTO.builder().id(UUID.randomUUID()).categoryName("Fashions").available(true)
				.createdOn(OffsetDateTime.parse("2026-05-14T10:15:30+00:00")).createdBy("Admin").variants(varList)
				.build());

		category = CategoriesDTO.CategoryDTO.builder().id(UUID.fromString("b516f577-11da-424e-9ad0-bc23ab15df1b"))
				.categoryName("Fashions").available(true).createdOn(OffsetDateTime.parse("2026-05-14T10:15:30+00:00"))
				.createdBy("Admin").variants(varList).build();

		cat = CategoriesDTO.builder().categories(list).build();

		recommendedProds = prodList1;

	}

	@Test
	void testGetProducts() throws Exception {
		when(productService.getAllProducts()).thenReturn(cat);

		mockMvc.perform(get("/api/v1/categories").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();

		verify(productService, times(1)).getAllProducts();
	}

	@Test
	void testAddProducts() throws Exception {
		when(productService.saveCategories(category)).thenReturn(category);

		mockMvc.perform(post("/api/v1/categories/addcategory").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(category))).andExpect(status().isOk()).andReturn();

		verify(productService, times(1)).saveCategories(category);

	}

	@Test
	void testFindProductById() throws Exception {
		UUID id = UUID.fromString("b516f577-11da-424e-9ad0-bc23ab15df1b");

		when(productService.findById(id)).thenReturn(category);

		mockMvc.perform(get("/api/v1/categories/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		verify(productService, times(1)).findById(id);
	}

	@Test
	void testUpdateProducts() throws Exception {
		when(productService.saveCategories(category)).thenReturn(category);

		mockMvc.perform(put("/api/v1/categories/updatecategory").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(category))).andExpect(status().isOk()).andReturn();

		verify(productService, atLeastOnce()).saveCategories(category);
	}

	@Test
	void testGetRecommendationProds() throws Exception {
		UUID prodId = UUID.fromString("7fb2814b-b984-472b-bb22-ac69651c2859");

		when(productService.getRecommendedProducts(prodId)).thenReturn(recommendedProds);

		mockMvc.perform(get("/api/v1/categories/recommendation/{prodItemId}", prodId)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		verify(productService, times(1)).getRecommendedProducts(prodId);
	}
	/*
	 * @Test void testGetVariants() { assertTrue(true); }
	 * 
	 * @Test void testUpdateQuantity() { assertTrue(true); }
	 * 
	 * @Test void testFilterCategoryBy() { assertTrue(true); }
	 * 
	 * @Test void testCheckQuantity() { assertTrue(true); }
	 */
}
