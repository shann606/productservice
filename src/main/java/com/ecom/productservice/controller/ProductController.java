package com.ecom.productservice.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {
	
	
	@GetMapping("/{productId}")
	private ResponseEntity addProduct(@PathVariable("productId") UUID productId)
	{
		System.out.println("printing product id "+ productId);
		return null;
	}

}
