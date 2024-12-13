package com.bytebattler.Restaurant.Services;

import com.bytebattler.Restaurant.Models.MenuModel;
import com.bytebattler.Restaurant.Repository.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
	private final static Logger logger = LoggerFactory.getLogger(MenuService.class);
	private final MenuRepository menuRepository;

	@Autowired
	public MenuService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}


	public boolean addItem(MenuModel item) {
		try {
			Optional<MenuModel> existingItem = menuRepository.findByItemName(item.getItemName());
			if (existingItem.isEmpty()) {
				menuRepository.save(item);
				return true;
			} else {
				logger.info("Item with name {} already exists", item.getItemName());
				return false;
			}
		} catch (Exception e) {
			logger.error("Error while adding item: ", e);
			return false;
		}
	}

	public List<MenuModel> getAllItems() {
		return menuRepository.findAll();
	}

	public Optional<MenuModel> getItemById(String id) {
		return menuRepository.findById(id);
	}

	public boolean deleteItemById(String id) {
		if (menuRepository.findById(id).isPresent()) {
			menuRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteAllItems() {
		if (!menuRepository.findAll().isEmpty()) {
			menuRepository.deleteAll();
			return true;
		} else {
			return false;
		}
	}

	public boolean updateItem(String id, MenuModel item) {
		try {
			Optional<MenuModel> oldItemOptional = menuRepository.findById(id);
			if (oldItemOptional.isPresent()) {
				MenuModel oldItem = oldItemOptional.get();
				if (oldItem.getIsAvailable() != null && oldItem.getIsAvailable()) {
					if (item.getItemName() != null && !item.getItemName().isEmpty()) {
						oldItem.setItemName(item.getItemName());
					}
					if (item.getDescription() != null && !item.getDescription().isEmpty()) {
						oldItem.setDescription(item.getDescription());
					}
					if (item.getPrice() != null) {
						oldItem.setPrice(item.getPrice());
					}
					if (item.getImageUrl() != null && !item.getImageUrl().isEmpty()) {
						oldItem.setImageUrl(item.getImageUrl());
					}
					if (item.getIsAvailable() != null) {
						oldItem.setIsAvailable(item.getIsAvailable());
					}
					menuRepository.save(oldItem);
					return true;
				} else {
					logger.error("Item with id {} is not available", id);
					return false;
				}
			} else {
				logger.error("Item with id {} not found", id);
				return false;
			}
		} catch (Exception e) {
			logger.error("Error while updating item: ", e);
			return false;
		}
	}

}


