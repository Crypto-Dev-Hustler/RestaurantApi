package com.bytebattler.Restaurant.Controllers.MenuManagement;

import com.bytebattler.Restaurant.Models.MenuModel;
import com.bytebattler.Restaurant.Services.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuManage {
	private static final Logger logger = LoggerFactory.getLogger(MenuManage.class);
	private final MenuService menuService;

	@Autowired
	public MenuManage(MenuService menuService) {
		this.menuService = menuService;
	}

	@PostMapping("/addItem")
	public ResponseEntity<?> additem(@RequestBody MenuModel item) {
		if (item == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data Not Provided");
		}
		try {
			boolean isAdded = menuService.addItem(item);
			if (isAdded) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Item added successfully");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add item");
			}
		} catch (Exception e) {
			logger.error(String.valueOf(e.getCause()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
	}

	@GetMapping("getItem")
	public ResponseEntity<?> getItem(@RequestParam(required = false) String id) {
		if (id == null) {
			List<MenuModel> items = menuService.getAllItems();
			if (items.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(items);
			}
		} else {
			Optional<MenuModel> item = menuService.getItemById(id);
			if (menuService.getItemById(id).isPresent()) {
				return ResponseEntity.status(HttpStatus.FOUND).body(item);
			} else {
				return ResponseEntity.notFound().build();
			}
		}
	}

	@PutMapping("/UpdateItem")
	public ResponseEntity<?> updateItem(@RequestParam String id, @RequestBody MenuModel item) {
		try {
			boolean flag = menuService.updateItem(id, item);
			if (flag) {
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			logger.error(String.valueOf(e.getCause()));
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("DeleteItem")
	public ResponseEntity<?> deleteItem(@RequestParam(required = false) String id) {
		if (id == null) {
			boolean flag = menuService.deleteAllItems();
			if (flag) {
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			boolean flag = menuService.deleteItemById(id);
			if (flag) {
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}

	}
}
