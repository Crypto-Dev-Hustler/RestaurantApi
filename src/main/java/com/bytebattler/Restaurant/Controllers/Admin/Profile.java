package com.bytebattler.Restaurant.Controllers.Admin;

import com.bytebattler.Restaurant.Models.UserModel;
import com.bytebattler.Restaurant.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class Profile {

	@Autowired
	UserService userService;

	@RequestMapping(value = "Get", method = RequestMethod.GET)
	public ResponseEntity<?> getusers(@RequestParam(required = false) Long id) {
		if (id == null) {
			List<UserModel> users = new ArrayList<>();
			users = userService.getAllUsers();
			if (users.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There is no USERS");
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(STR."Users found : \n \{users}");
			}
		} else {
			Optional<UserModel> user = userService.getUserById(id);
			if (user.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
			} else {
//				Gson gson=new Gson();
//				String json = gson.toJson(user);
				return ResponseEntity.status(HttpStatus.FOUND).body(STR."User Found :\n \{user}");
			}
		}
	}
}
