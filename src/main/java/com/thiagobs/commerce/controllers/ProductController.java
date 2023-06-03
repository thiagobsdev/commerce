package com.thiagobs.commerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thiagobs.commerce.dto.ProductDTO;
import com.thiagobs.commerce.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO>  findByID ( @PathVariable Long id) {
		ProductDTO dto  = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public  ResponseEntity<Page<ProductDTO>> findAll(
			@RequestParam(name = "name",defaultValue = "") String name, Pageable pageable) {
		Page<ProductDTO> dto = service.findAll(name,pageable);
		return ResponseEntity.ok(dto);
		
		
	}
	
	
	@PostMapping
	public ResponseEntity<ProductDTO> insert ( @Valid @RequestBody ProductDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
				
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO>  update ( @PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
		 dto  = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  delete ( @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
