package com.bytebattler.Restaurant.Services;

import com.bytebattler.Restaurant.Exceptions.InvalidUserException;
import com.bytebattler.Restaurant.Exceptions.UserAlreadyExistsException;
import com.bytebattler.Restaurant.Models.UserModel;
import com.bytebattler.Restaurant.Models.UserRoles;
import com.bytebattler.Restaurant.Repository.RoleRepository;
import com.bytebattler.Restaurant.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;


	@Transactional
	public boolean saveUser(UserModel user) {
		Set<UserRoles> roles = new HashSet<>();

		UserRoles checkRole = roleRepository.findByRoleName("USER");
		if (checkRole == null) {
			UserRoles defaultRole = new UserRoles();
			defaultRole.setRoleName("USER");
			checkRole = roleRepository.save(defaultRole); // Save the default role
			logger.info("Created default role: USER");
		}
		roles.add(checkRole);

		if (user.getRoles() != null) {
			for (UserRoles role : user.getRoles()) {
				UserRoles existingRole = roleRepository.findByRoleName(role.getRoleName());
				if (existingRole != null) {
					roles.add(existingRole);
				} else {
					UserRoles newRole = new UserRoles();
					newRole.setRoleName(role.getRoleName());
					UserRoles savedRole = roleRepository.save(newRole);
					roles.add(savedRole);
				}
			}
		}


		if (!user.getPassword().isEmpty()) {
//			user.setPassword(passwordEncoder.encode(user.getPassword())); // Use PasswordEncoder
			user.setPassword(user.getPassword()); // Use simple text password for now TODO: add password encoder after
		}
		user.setRoles(roles);
		user.setUser_id(null);
		validateUser(user);
		try {
			userRepository.save(user);
			return true;
		} catch (DataIntegrityViolationException ex) {
			logger.error("Duplicate User", new UserAlreadyExistsException("User  with this username or email already exists"));
			return false;

		}
	}

	private void validateUser(UserModel user) {
		if (user.getUserName() == null || user.getUserName().length() < 1) {
			throw new InvalidUserException("Username must be at least 1 character long");
		}
		if (user.getUserEmail() != null && !isValidEmail(user.getUserEmail())) {
			throw new InvalidUserException("Invalid email format");
		}
	}

	private boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return email.matches(emailRegex);
	}
}