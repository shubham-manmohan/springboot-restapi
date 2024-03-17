package com.cashrich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cashrich.dto.SignupRequestDTO;
import com.cashrich.entity.User;
import com.cashrich.service.UserService;
import com.cashrich.utils.ValidationUtils;

@RestController
public class SignupController {

	@Autowired
	private UserService userService;

	@Autowired
	private ValidationUtils validationUtils;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody SignupRequestDTO request) {
		if (request.getUsername() == null || request.getPassword() == null) {
			return ResponseEntity.badRequest().body(new String("Please Enter username or password"));
		}
		if (userService.existsByEmail(request.getEmail()) || userService.existsByUsername(request.getUsername())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new String("Email or username already exists"));
		}

		if (!validationUtils.isUsernameValid(request.getUsername())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(
					"Invalid username (Username has to be character and digits only with length of 4 to 15)"));
		}
		if (!validationUtils.isPasswordValid(request.getPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(
					"Invalid password (Password has to be 8 to 15 in length with mix of at least 1 upper, 1 lower, 1 digit and 1 special character)"));
		}

		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setMobile(request.getMobile());
		user.setUsername(request.getUsername());
		user.setPassword(request.getPassword());
		userService.save(user);
		return ResponseEntity.ok(new String("User Registered successfully ..."));
	}
}