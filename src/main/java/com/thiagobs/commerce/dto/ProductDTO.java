package com.thiagobs.commerce.dto;

import java.util.ArrayList;
import java.util.List;

import com.thiagobs.commerce.entities.Category;
import com.thiagobs.commerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {

	private Long id;
	
	@NotBlank(message = "O nome não pode estar vazio")
	@Size(min = 3, max = 80, message = "O Nome deve ter entre 03 e 80 caracteres")
	private String name;
	
	@Size(min = 10, message = "A descrição deve ter no minimo 10 caracteres")
	private String description;
	
	@Positive(message = "O preço deve ser positivo")
	private Double price;
	private String imgUrl;
	
	@NotEmpty
	private List< CategoryDTO> categories = new ArrayList<>();
	
	public ProductDTO() {
	}

	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	public ProductDTO( Product entity) {
		id = entity.getId();
		name  = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();
		for ( Category cat : entity.getCategories()) {
			categories.add(new CategoryDTO(cat));
		}
		
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}
	
	
	
	

}
