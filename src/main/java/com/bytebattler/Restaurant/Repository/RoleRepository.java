package com.bytebattler.Restaurant.Repository;

import com.bytebattler.Restaurant.Models.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRoles, Long> {
	UserRoles findByRoleName(String roleName);
}
