package com.thiagobs.commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagobs.commerce.dto.ProductDTO;
import com.thiagobs.commerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@GetMapping("/{id}")
	public ProductDTO  findByID ( @PathVariable Long id) {
		ProductDTO dto  = service.findById(id);
		return dto;
	}
	
	@GetMapping
	public Page<ProductDTO> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}
}
