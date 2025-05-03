package com.hulkhiretech.payments.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import lombok.Data;
@Data
public class HttpRequest {
	private HttpMethod httpmethod;
	
	private String url;
	private HttpHeaders headers;
	private Object requestBody;
	

}
