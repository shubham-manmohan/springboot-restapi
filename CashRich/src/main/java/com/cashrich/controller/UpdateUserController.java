package com.cashrich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cashrich.dto.UpdateUserRequestDTO;
import com.cashrich.entity.User;
import com.cashrich.service.TokenService;
import com.cashrich.service.UserService;

@RestController
public class UpdateUserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/update")
	public ResponseEntity<String> updateUser(@RequestHeader("Authorization") String authentication,@RequestBody UpdateUserRequestDTO request) {
		if (authentication == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new String("Authentication Required (JWT)"));
		}
		String username = tokenService.extractUsername(authentication.replace("Bearer ", "").trim());

		User existingUser = userService.findByUsername(username);
		if (existingUser == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String("User not found."));
		}

		if (!username.equals(request.getUsername())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new String("Unauthorized Attempt."));
		}

		existingUser.setFirstName(request.getFirstName());
		existingUser.setLastName(request.getLastName());
		existingUser.setMobile(request.getMobile());
		existingUser.setPassword(request.getPassword());
		userService.save(existingUser);

		return ResponseEntity.ok(new String("User updated successfully"));
	}
}