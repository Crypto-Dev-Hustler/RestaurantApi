package com.bytebattler.Restaurant.Services;

import com.bytebattler.Restaurant.Models.UserRoles;
import com.bytebattler.Restaurant.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public List<UserRoles> getAllRoles() {
		return roleRepository.findAll();
	}

	public UserRoles getRoleById(Long id) {
		return roleRepository.findById(id).orElse(null);
	}

	public UserRoles createRole(UserRoles role) {
		return roleRepository.save(role);
	}

	public UserRoles updateRole(Long id, UserRoles roleDetails) {
		UserRoles role = getRoleById(id);
		if (role != null) {
			role.setRoleName(roleDetails.getRoleName());
			return roleRepository.save(role);
		}
		return null;
	}

	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
	}
}