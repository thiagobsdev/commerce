package com.thiagobs.commerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.thiagobs.commerce.entities.Order;
import com.thiagobs.commerce.entities.OrderItem;
import com.thiagobs.commerce.entities.OrderStatus;

import jakarta.validation.constraints.NotEmpty;

public class OrderDTO {

	private Long id;
	
	private Instant moment;
	
	private OrderStatus orderStatus;
	
	private ClientDTO client;
	
	private PaymentDTO payment;
	
	@NotEmpty(message = "A lista de items não pode estar vazia")
	private List<OrderItemDTO > items = new ArrayList<>();
	

	public OrderDTO(Long id, Instant moment, OrderStatus orderStatus, ClientDTO client, PaymentDTO payment) {
		super();
		this.id = id;
		this.moment = moment;
		this.orderStatus = orderStatus;
		this.client = client;
		this.payment = payment;
	}
	
	public OrderDTO(Order entity) {
		id = entity.getId();
		moment = entity.getMoment();
		orderStatus = entity.getOrderStatus();
		client =  new ClientDTO(entity.getClient());
		payment = ( entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment()) ;
		for (OrderItem orderItem : entity.getItems() ) {
			items.add(new OrderItemDTO(orderItem));
		}
	
	}

	public Long getId() {
		return id;
	}

	public Instant getMoment() {
		return moment;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public ClientDTO getClient() {
		return client;
	}

	public PaymentDTO getPayment() {
		return payment;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}
	
	public Double getTotal() {
		Double sum = 0.0;
		for (OrderItemDTO orderDTo : items ) {
			sum += orderDTo.getSubtotal();
		}
		
		return sum;
	}
	
	
	

}
