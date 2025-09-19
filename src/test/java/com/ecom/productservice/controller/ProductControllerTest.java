package com.ecom.productservice.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ecom.productservice.dto.CategoriesDTO;
import com.ecom.productservice.dto.CategoriesDTO.CategoryDTO;
import com.ecom.productservice.dto.ProductItemsDTO;
import com.ecom.productservice.dto.ProductsDTO;
import com.ecom.productservice.dto.VariantsDTO;
import com.ecom.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private ProductService productSerivce;

	private static CategoriesDTO cat;
	private static CategoriesDTO.CategoryDTO category;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void test() {
		assertTrue(true);
	}

	@BeforeAll
	public static void getCategoires() {
		List<CategoryDTO> list = new ArrayList<CategoriesDTO.CategoryDTO>();
		List<VariantsDTO> varList = new ArrayList<VariantsDTO>();
		List<ProductItemsDTO> itemList = new ArrayList<ProductItemsDTO>();
		List<ProductsDTO> prodList1 = new ArrayList<ProductsDTO>();
		List<ProductsDTO> prodList2 = new ArrayList<ProductsDTO>();
		prodList1.add(ProductsDTO.builder().id(UUID.randomUUID()).available(true).brand("Polo")
				.description("PoloTshits will fit good").createdOn(OffsetDateTime.now()).createdBy("Admin")
				.price(new BigDecimal(1000)).build());
		prodList1.add(ProductsDTO.builder().id(UUID.randomUUID()).available(true).brand("Jockey")
				.description("Jockey Tshits will fit good").createdOn(OffsetDateTime.now()).createdBy("Admin")
				.price(new BigDecimal(2000)).build());
		prodList2.add(ProductsDTO.builder().id(UUID.randomUUID()).available(true).brand("Peter England")
				.description("Formal PE will fit good").createdOn(OffsetDateTime.now()).createdBy("Admin")
				.price(new BigDecimal(400)).build());
		prodList2.add(ProductsDTO.builder().id(UUID.randomUUID()).available(true).brand("Bombay Dying")
				.description("Formal Bombay Dying will fit good").createdOn(OffsetDateTime.now()).createdBy("Admin")
				.price(new BigDecimal(1000)).build());

		itemList.add(ProductItemsDTO.builder().id(UUID.randomUUID()).available(true).productItemName("Formal Shirts")
				.createdOn(OffsetDateTime.now()).createdBy("Admin").products(prodList2).build());
		itemList.add(ProductItemsDTO.builder().id(UUID.randomUUID()).available(true).productItemName("T Shirts")
				.createdOn(OffsetDateTime.now()).createdBy("Admin").products(prodList1).build());
		varList.add(VariantsDTO.builder().id(UUID.randomUUID()).available(true).createdOn(OffsetDateTime.now())
				.createdBy("Admin").variantName("Men's Clothing").productItems(itemList).build());
		list.add(CategoriesDTO.CategoryDTO.builder().id(UUID.randomUUID()).categoryName("Fashions").available(true)
				.createdOn(OffsetDateTime.now()).createdBy("Admin").variants(varList).build());

		category = CategoriesDTO.CategoryDTO.builder().id(UUID.fromString("b516f577-11da-424e-9ad0-bc23ab15df1b"))
				.categoryName("Fashions").available(true).createdOn(OffsetDateTime.now()).createdBy("Admin")
				.variants(varList).build();

		cat = CategoriesDTO.builder().categories(list).build();

	}

	@Test
	@Description("Testing ProductController : for All the products")
	public void getProducts() throws Exception {

		when(productSerivce.getAllProducts()).thenReturn(cat);

		mockMVC.perform(get("/api/v1/products/getproducts").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		verify(productSerivce, atLeastOnce()).getAllProducts();

	}

	@Test
	@Description("Testing ProductController : find by productId")
	public void getProductByid() throws Exception {

		UUID id = UUID.fromString("b516f577-11da-424e-9ad0-bc23ab15df1b");

		when(productSerivce.findById(id)).thenReturn(category);

		mockMVC.perform(get("/api/v1/products/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		verify(productSerivce, atLeastOnce()).findById(id);

	}

	@Test
	@Description("Testing ProductController : creating new product")
	public void addNewProduct() throws Exception {

		when(productSerivce.saveCategories(category)).thenReturn(category);

		mockMVC.perform(post("/api/v1/products/newproduct").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(category))).andExpect(status().isOk()).andReturn();

		// verify(productSerivce, atLeastOnce()).saveCategories(category);

	}

	@Test
	@Description("Testing ProductController : updating exiting product")
	public void updateProduct() throws Exception {

		when(productSerivce.saveCategories(category)).thenReturn(category);

		mockMVC.perform(put("/api/v1/products/updateproduct").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(category))).andExpect(status().isOk()).andReturn();

		// verify(productSerivce, atLeastOnce()).saveCategories(category);

	}

}
