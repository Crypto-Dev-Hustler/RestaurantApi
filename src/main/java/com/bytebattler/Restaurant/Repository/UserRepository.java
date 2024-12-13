package com.bytebattler.Restaurant.Repository;

import com.bytebattler.Restaurant.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserModel, Long> {
	UserModel findByUserName(String username);
}
