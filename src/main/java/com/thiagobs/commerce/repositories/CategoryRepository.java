package com.thiagobs.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagobs.commerce.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
