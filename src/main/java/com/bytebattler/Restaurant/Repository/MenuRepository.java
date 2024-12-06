package com.bytebattler.Restaurant.Repository;

import com.bytebattler.Restaurant.Models.MenuModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends MongoRepository<MenuModel,String> {
	public boolean findByItemName(String name);
}
