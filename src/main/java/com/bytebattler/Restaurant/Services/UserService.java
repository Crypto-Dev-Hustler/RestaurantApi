package com.bytebattler.Restaurant.Services;

import com.bytebattler.Restaurant.Exceptions.InvalidUserException;
import com.bytebattler.Restaurant.Exceptions.UserAlreadyExistsException;
import com.bytebattler.Restaurant.Exceptions.UserNotFoundException;
import com.bytebattler.Restaurant.Models.UserModel;
import com.bytebattler.Restaurant.Models.UserRoles;
import com.bytebattler.Restaurant.Repository.RoleRepository;
import com.bytebattler.Restaurant.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

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
			user.setPassword(passwordEncoder.encode(user.getPassword()));
//		user.setPassword(user.getPassword());
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

	public List<UserModel> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<UserModel> getUserById(Long id) {
		return userRepository.findById(id);
	}

	public boolean updateUser(long id, UserModel newUser) {
		UserModel oldUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));

		if (!newUser.getUserName().isBlank()) {
			oldUser.setUserName(newUser.getUserName());
		}
		if (!newUser.getUserEmail().isBlank()) {
			oldUser.setUserEmail(newUser.getUserEmail());
		}
		if (!newUser.getPassword().isBlank()) {
			oldUser.setPassword(newUser.getPassword());
		}
		if (!newUser.getPhoneNumber().isBlank()) {
			oldUser.setPhoneNumber(newUser.getPhoneNumber());
		}
//		if (!newUser.getRoles().isEmpty()) {
//			Set<UserRoles> roles = new HashSet<>();
//			roles.add((UserRoles) oldUser.getRoles());
//			roles.add((UserRoles) newUser.getRoles());
//			oldUser.setRoles(roles);
//		}

		try {
			userRepository.save(oldUser);
			return true;
		} catch (Exception e) {
			logger.error("User Not Update");
			return false;
		}
	}

	public boolean deleteUser(Long id) {
		UserModel oldUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
		userRepository.deleteById(id);
		return true;
	}
}


