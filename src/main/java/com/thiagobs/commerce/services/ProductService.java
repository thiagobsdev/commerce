package com.thiagobs.commerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thiagobs.commerce.dto.CategoryDTO;
import com.thiagobs.commerce.dto.ProductDTO;
import com.thiagobs.commerce.dto.ProductMinDTO;
import com.thiagobs.commerce.entities.Category;
import com.thiagobs.commerce.entities.Product;
import com.thiagobs.commerce.repositories.ProductRepository;
import com.thiagobs.commerce.services.exceptions.DataBaseException;
import com.thiagobs.commerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product product = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

		ProductDTO dto = new ProductDTO(product);
		return dto;

	}

	@Transactional
	public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
		Page<Product> result = repository.searchByName(name, pageable);
		return result.map(x -> new ProductMinDTO(x));

	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product product = new Product();
		copyDtoTOEntity(dto, product);
		return new ProductDTO(repository.save(product));
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {

		try {

			Product product = repository.getReferenceById(id);

			copyDtoTOEntity(dto, product);

			return new ProductDTO(repository.save(product));

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado ao atualizar");
		}

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {

		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Falha de integridade referencial");
		}
	}

	private void copyDtoTOEntity(ProductDTO dto, Product product) {
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setImgUrl(dto.getImgUrl());
		
		product.getCategories().clear();
		for (CategoryDTO cat : dto.getCategories() ) {
			Category category = new Category();
			category.setId(cat.getId() );
			product.getCategories().add(category);
		}

	}

}
