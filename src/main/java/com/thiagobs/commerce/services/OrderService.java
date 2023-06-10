package com.thiagobs.commerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiagobs.commerce.dto.OrderDTO;
import com.thiagobs.commerce.dto.OrderItemDTO;
import com.thiagobs.commerce.entities.Order;
import com.thiagobs.commerce.entities.OrderItem;
import com.thiagobs.commerce.entities.OrderStatus;
import com.thiagobs.commerce.entities.Product;
import com.thiagobs.commerce.repositories.OrderItemRepository;
import com.thiagobs.commerce.repositories.OrderRepository;
import com.thiagobs.commerce.repositories.ProductRepository;
import com.thiagobs.commerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	OrderRepository repository;

	@Autowired
	UserService userService;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	AuthService authService;

	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		
		authService.verififyRoleAndClient(order.getClient().getId());

		OrderDTO dto = new OrderDTO(order);
		
		return dto;

	}

	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order();

		order.setMoment(Instant.now());
		order.setOrderStatus(OrderStatus.WAINTING_PAYMENT);
		order.setClient(userService.findUserAutent());
		order.setPayment(null);

		for (OrderItemDTO orderDto : dto.getItems()) {
			Product product = productRepository.getReferenceById(orderDto.getProductId());
			OrderItem orderItem = new OrderItem(order, product, orderDto.getQuantity(), orderDto.getPrice());
			order.getItems().add(orderItem);

		}

		repository.save(order);
		orderItemRepository.saveAll(order.getItems());

		return new OrderDTO(order);

	}

}
