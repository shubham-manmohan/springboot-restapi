package com.cashrich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cashrich.dto.ApiResponseDTO;
import com.cashrich.entity.ApiRequestResponse;
import com.cashrich.service.ApiService;
import com.cashrich.service.TokenService;

@RestController
public class CoinController {

	@Autowired
	private ApiService apiService;

	@Autowired
	private TokenService tokenService;

	@GetMapping("/api/coin")
	public ResponseEntity<ApiResponseDTO> makeApiCall(@RequestHeader("Authorization") String authentication,
			@RequestParam String symbol) {
		if (authentication == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ApiResponseDTO("Authentication Required (JWT)"));
		}
		String username = tokenService.extractUsername(authentication.replace("Bearer ", "").trim());
		ApiRequestResponse response = apiService.makeApiCall(username, symbol);
		ApiResponseDTO res = new ApiResponseDTO("Success");
		res.setResponse(response);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
	}
}
