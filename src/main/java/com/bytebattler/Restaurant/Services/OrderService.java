package com.bytebattler.Restaurant.Services;


import com.bytebattler.Restaurant.Models.OrderModel;
import com.bytebattler.Restaurant.Repository.OrderRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class)

	@Autowired
	private OrderRepository orderRepository;

	// Create
	public OrderModel createOrder(OrderModel order) {
		return orderRepository.save(order);
	}

	// Read
	public List<OrderModel> getAllOrders() {
		return orderRepository.findAll();
	}

	public Optional<OrderModel> getOrderById(Long id) {
		return orderRepository.findById(id);
	}

	// Update
	public boolean updateOrder(Long id, OrderModel orderDetails) {
		Optional<OrderModel> existingOrderOptional = orderRepository.findById(id);
		if (existingOrderOptional.isPresent()) {
			OrderModel existingOrder = existingOrderOptional.get();
			existingOrder.setStatus(orderDetails.getStatus());
			existingOrder.setItems(orderDetails.getItems());
			orderRepository.save(existingOrder);
			return true;
		} else {
			return false;
		}
	}

	// Delete
	public boolean deleteOrder(Long id) {
		if (orderRepository.existsById(id)) {
			orderRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteOrderById(Long id) {
		if (orderRepository.existsById(id)) {
			orderRepository.deleteById(id);
			return true;
		} else {
			logger.error("Order with id {} not found", id);
			return false;
		}
	}

}
