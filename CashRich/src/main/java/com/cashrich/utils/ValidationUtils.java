package com.cashrich.utils;

import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {

	public boolean isUsernameValid(String username) {
		return username != null && username.matches("^[a-zA-Z0-9]{4,15}$");
	}

	public boolean isPasswordValid(String password) {
		return password != null
				&& password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$");
	}
}