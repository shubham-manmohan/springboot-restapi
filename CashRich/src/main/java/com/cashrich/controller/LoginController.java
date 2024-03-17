package com.cashrich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cashrich.dto.LoginRequestDTO;
import com.cashrich.entity.User;
import com.cashrich.service.TokenService;
import com.cashrich.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;

	@Autowired
	HttpSession session;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequestDTO request) {
		if (request.getUsername() == null || request.getPassword() == null) {
			return ResponseEntity.badRequest().body(new String("Please Enter username & password .."));
		}
		User user = userService.findByUsername(request.getUsername());
		if (user == null || !userService.isPasswordValid(user, request.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new String("Invalid Crediential .. "));
		}

		String token = tokenService.generateToken(user);
		session.setAttribute("username", user.getUsername());
		return ResponseEntity.ok(new String(token));
	}
}