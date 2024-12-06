package com.bytebattler.Restaurant.Controllers.SignUp;

import com.bytebattler.Restaurant.Models.UserModel;
import com.bytebattler.Restaurant.Services.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SignUp {
	@Autowired
	UserService userService;

	@PostMapping("/")
	ResponseEntity<String> signup(@RequestBody UserModel user) {
		userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
	}



}