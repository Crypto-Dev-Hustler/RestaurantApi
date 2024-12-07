package com.bytebattler.Restaurant.Repository;

import com.bytebattler.Restaurant.Models.MenuModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends MongoRepository<MenuModel, String> {
	@Query("{itemName:?0}")
	public Optional<MenuModel> findByItemName(String name);
}
