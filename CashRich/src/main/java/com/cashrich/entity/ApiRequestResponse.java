package com.cashrich.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ApiRequestResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private String requestParameters;
	@Column(columnDefinition = "TEXT")
	private String responseData;
	private LocalDateTime requestTimestamp;
	private LocalDateTime responseTimestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRequestParameters() {
		return requestParameters;
	}

	public void setRequestParameters(String requestParameters) {
		this.requestParameters = requestParameters;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	public LocalDateTime getRequestTimestamp() {
		return requestTimestamp;
	}

	public void setRequestTimestamp(LocalDateTime requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}

	public LocalDateTime getResponseTimestamp() {
		return responseTimestamp;
	}

	public void setResponseTimestamp(LocalDateTime responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}

}