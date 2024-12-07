package com.bytebattler.Restaurant.Services;


import com.bytebattler.Restaurant.Models.MenuModel;
import com.bytebattler.Restaurant.Models.OrderModel;
import com.bytebattler.Restaurant.Repository.MenuRepository;
import com.bytebattler.Restaurant.Repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private MenuRepository menuRepository;

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
			if (!orderDetails.getStatus().isEmpty()) {
				existingOrder.setStatus(orderDetails.getStatus());
			}
			if (!orderDetails.getItems().isEmpty()) {
				existingOrder.setItems(orderDetails.getItems());
			}
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

	public boolean deleteAllOrder() {
		orderRepository.deleteAll();
		return true;
	}

	public OrderModel getOrderWithItems(Long id) {
		Optional<OrderModel> orderOptional = orderRepository.findById(id);
		if (orderOptional.isPresent()) {
			OrderModel order = orderOptional.get();
			List<MenuModel> items = menuRepository.findAllById(order.getItems());
			List<String> formatedItem = formatMenuItems(items);
			order.setItems(formatedItem);

			return order;
		} else {
			return null;
		}
	}


	private List<String> formatMenuItems(List<MenuModel> items) {
		return items.stream().map(item -> String.format("Item Name: %s " +
						"Description: %s" +
						"Price: %.2f" +
						"Available: %s" +
						"Image URL: %s",
				item.getItemName(), item.getDescription(), item.getPrice(), item.getIsAvailable() ? "Yes" : "No", item.getImageUrl())).collect(Collectors.toList());
	}
}


