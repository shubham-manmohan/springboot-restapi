package com.cashrich.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cashrich.entity.ApiRequestResponse;
import com.cashrich.repository.ApiRequestResponseRepository;

@Service
public class ApiService {

	@Autowired
	private ApiRequestResponseRepository repository;

	public ApiRequestResponse makeApiCall(String username, String symbol) {
		String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=" + symbol;
		String apiKey = "27ab17d1-215f-49e5-9ca4-afd48810c149";
		String requestParameters = "symbol=" + symbol;
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-CMC_PRO_API_KEY", apiKey);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		String responseData = responseEntity.getBody();

		ApiRequestResponse apiRequestResponse = new ApiRequestResponse();
		apiRequestResponse.setUsername(username);
		apiRequestResponse.setRequestParameters(requestParameters);
		apiRequestResponse.setResponseData(responseData);
		apiRequestResponse.setRequestTimestamp(LocalDateTime.now());
		apiRequestResponse.setResponseTimestamp(LocalDateTime.parse(getResponseTimestamp(responseData)));
		repository.save(apiRequestResponse);
		return apiRequestResponse;
	}

	private String getResponseTimestamp(String responseData) {
		try {
			JSONObject jsonResponse = new JSONObject(responseData);
		    JSONObject status = jsonResponse.getJSONObject("status");
		    String timestampString = status.getString("timestamp");
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		    return LocalDateTime.parse(timestampString, formatter).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return LocalDateTime.now().toString();
		}
	}
}