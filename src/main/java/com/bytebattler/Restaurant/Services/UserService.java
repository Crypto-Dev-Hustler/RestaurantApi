package com.bytebattler.Restaurant.Services;

import com.bytebattler.Restaurant.Exceptions.InvalidUserException;
import com.bytebattler.Restaurant.Exceptions.UserAlreadyExistsException;
import com.bytebattler.Restaurant.Models.UserModel;
import com.bytebattler.Restaurant.Models.UserRoles;
import com.bytebattler.Restaurant.Repository.RoleRepository;
import com.bytebattler.Restaurant.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;


	@Transactional
	public UserModel saveUser(UserModel user) {
		Set<UserRoles> roles = new HashSet<>();

		if (user.getRoles() != null) { // Fix the null check
			for (UserRoles role : user.getRoles()) {
				UserRoles existingRole = roleRepository.findByRoleName(role.getRoleName());
				if (existingRole != null) {
					roles.add(existingRole);
				} else {
					// Create a new role if it doesn't exist
					UserRoles newRole = new UserRoles();
					newRole.setRoleName(role.getRoleName());
					UserRoles savedRole = roleRepository.save(newRole);
					roles.add(savedRole);
				}
			}
		}

		// Always add the default role
		UserRoles defaultRole = roleRepository.findByRoleName("USER");
		if (defaultRole != null) {
			roles.add(defaultRole);
		}

		if (!user.getPassword().isEmpty()) {
//			user.setPassword(passwordEncoder.encode(user.getPassword())); // Use PasswordEncoder
			user.setPassword(user.getPassword()); // Use simple text password for now TODO: add password encoder after
		}
		user.setRoles(roles);
		user.setUser_id(null);
		validateUser(user);
		try {
			return userRepository.save(user);
		} catch (DataIntegrityViolationException ex) {
			throw new UserAlreadyExistsException("User  with this username or email already exists");
		}
	}

	private void validateUser(UserModel user) {
		if (user.getUserName() == null || user.getUserName().length() < 1) {
			throw new InvalidUserException("Username must be at least 1 character long");
		}
		if (user.getUserEmail() != null && !isValidEmail(user.getUserEmail())) {
			throw new InvalidUserException("Inv alid email format");
		}
	}

	private boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; // Fix the regex
		return email.matches(emailRegex);
	}
}