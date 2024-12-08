package com.bytebattler.Restaurant.Controllers.OrderManagement;

import com.bytebattler.Restaurant.Models.OrderModel;
import com.bytebattler.Restaurant.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	// Fetch all orders
	@GetMapping("/get")
	public ResponseEntity<List<OrderModel>> getAllOrders() {
		List<OrderModel> orders = orderService.getAllOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// Fetch order by ID
	@GetMapping("/get/{id}")
	public ResponseEntity<OrderModel> getOrderById(@PathVariable Long id) {
		Optional<OrderModel> order = orderService.getOrderById(id);
		return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Fetch order with items from MongoDB
	@GetMapping("/getItem/{id}")
	public ResponseEntity<OrderModel> getOrderWithItems(@PathVariable Long id) {
		OrderModel orderWithItems = orderService.getOrderWithItems(id);
		if (orderWithItems != null) {
			return new ResponseEntity<>(orderWithItems, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Create a new order
	@PostMapping("/create")
	public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel order) {
		OrderModel createdOrder = orderService.createOrder(order);
		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}

	// Update an existing order
	@PutMapping("/update/{id}")
	public ResponseEntity<OrderModel> updateOrder(@PathVariable Long id, @RequestBody OrderModel orderDetails) {
		boolean isUpdated = orderService.updateOrder(id, orderDetails);
		if (isUpdated) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Delete an order by ID
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		boolean isDeleted = orderService.deleteOrder(id);
		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
