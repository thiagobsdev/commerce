package com.thiagobs.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagobs.commerce.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
