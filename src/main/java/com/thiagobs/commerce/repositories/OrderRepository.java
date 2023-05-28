package com.thiagobs.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagobs.commerce.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
