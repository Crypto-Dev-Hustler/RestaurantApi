package com.bytebattler.Restaurant.Services;

import com.bytebattler.Restaurant.Models.MenuModel;
import com.bytebattler.Restaurant.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
	private final MenuRepository menuRepository;

	@Autowired
	public MenuService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	public boolean addItem(MenuModel item) {
		if (menuRepository.findByItemName(item.getItemName())) {
			return false;
		} else {
			menuRepository.save(item);
			return true;
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

	public boolean updateItem(String id,MenuModel item){
		// TODO document why this method is empty
	}

}
