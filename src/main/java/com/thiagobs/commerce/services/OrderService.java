package com.thiagobs.commerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiagobs.commerce.dto.OrderDTO;
import com.thiagobs.commerce.entities.Order;
import com.thiagobs.commerce.repositories.OrderRepository;
import com.thiagobs.commerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	OrderRepository repository;

	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

		OrderDTO dto = new OrderDTO(order);
		return dto;

	}

	

}
