package com.cashrich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cashrich.entity.User;
import com.cashrich.repository.UserRepository;
import com.cashrich.utils.PasswordUtils;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void save(User user) {
		String encryptedPassword = PasswordUtils.encryptPassword(user.getPassword());
		user.setPassword(encryptedPassword);
		userRepository.save(user);
	}

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public boolean isPasswordValid(User user, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(password, user.getPassword());
	}
}