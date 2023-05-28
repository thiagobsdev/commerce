package com.thiagobs.commerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiagobs.commerce.dto.ProductDTO;
import com.thiagobs.commerce.entities.Product;
import com.thiagobs.commerce.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id ) {
		Optional<Product> result = repository.findById(id);
		Product product = result.get();
		ProductDTO dto = new ProductDTO(product);
		return dto;
		
	}
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> result = repository.findAll(pageable);
		return result.map(x -> new ProductDTO(x));
		
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		
		Product product = new Product();
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setImgUrl(dto.getImgUrl());
	
		return new ProductDTO(repository.save(product));
		
		
	}
		
		
	

}
