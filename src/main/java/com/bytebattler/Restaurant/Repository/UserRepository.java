package com.bytebattler.Restaurant.Repository;

import com.bytebattler.Restaurant.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
	boolean existsByUserName(String username);
}
