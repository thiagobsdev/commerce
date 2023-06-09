package com.thiagobs.commerce.dto;

import java.util.Objects;

import com.thiagobs.commerce.entities.Category;

public class CategoryDTO {
	
	private Long id;
	private String name;
	
	
	public CategoryDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public CategoryDTO(Category entity) {
		id = entity.getId();
		name = entity.getName();
	}


	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryDTO other = (CategoryDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}


	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", name=" + name + "]";
	}
	
	
	

}
