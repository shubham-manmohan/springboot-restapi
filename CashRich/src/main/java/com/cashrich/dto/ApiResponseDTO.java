package com.cashrich.dto;

import com.cashrich.entity.ApiRequestResponse;

public class ApiResponseDTO {

	private String message;
	private ApiRequestResponse response;
	

	public ApiResponseDTO(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApiRequestResponse getResponse() {
		return response;
	}

	public void setResponse(ApiRequestResponse response) {
		this.response = response;
	}

}
