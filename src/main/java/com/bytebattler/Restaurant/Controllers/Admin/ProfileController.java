package com.bytebattler.Restaurant.Controllers.Admin;

import com.bytebattler.Restaurant.Exceptions.UserNotFoundException;
import com.bytebattler.Restaurant.Models.UserModel;
import com.bytebattler.Restaurant.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class ProfileController {

	private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = "Get", method = RequestMethod.GET)
	public ResponseEntity<?> getusers(@RequestParam(required = false) Long id) {
		if (id == null) {
			List<UserModel> users = new ArrayList<>();
			users = userService.getAllUsers();
			if (users.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There is no USERS");
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(users.toString());
			}
		} else {
			Optional<UserModel> user = userService.getUserById(id);
			if (user.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
			} else {
				return ResponseEntity.status(HttpStatus.FOUND).body(user.toString());
			}
		}
	}

	@DeleteMapping("/Delete")
	public ResponseEntity<?> deleteUserById(@RequestParam Long id) {

		if (id != null) {
			try {
				boolean flag = userService.deleteUser(id);
				if (flag) return ResponseEntity.ok().build();
				else {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} catch (UserNotFoundException e) {
				log.info(STR."User not found with id :\{id}");
				log.error(e.getMessage());
				return ResponseEntity.badRequest().build();
			}
		}
		return ResponseEntity.badRequest().build();
	}
}
