package com.hulkhiretech.payments.service.helper;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.hulkhiretech.payments.constants.Constants;
import com.hulkhiretech.payments.http.HttpRequest;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class CaptureOrderHelper {
	
	public HttpRequest prepareHttpRequestForCaptureOrder(String orderId, String accessToken) {
		/*
		 * 1.call get AccessToken() API 2.Call Showorder(ShowOrder) API 3.handle
		 * response Success or failure 4.return response to controller
		 * 
		 */
		// header object creation
		log.info("In capture ");
		String captureOrderUrl = Constants.CaptureOrderUrl.replace("{orderId}", orderId); 
		log.info("CaptureOrderHelper:|captureOrderUrl:{}", captureOrderUrl);
		
		HttpHeaders headersobj = new HttpHeaders();
		headersobj.setBearerAuth(accessToken);
		headersobj.setContentType(MediaType.APPLICATION_JSON);
		headersobj.set("PayPal-Request-Id", orderId);
		
		log.info("CaptureOrderHelper:|headersobj:{}", headersobj);
		
		// Create Order object
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(captureOrderUrl);
		httpRequest.setHttpmethod(HttpMethod.POST);
		httpRequest.setHeaders(headersobj);
		httpRequest.setRequestBody(Constants.EMPTYSTRING);
	    httpRequest.setHeaders(headersobj);
		log.info("CaptureOrderHelper:|httpRequest:{}", httpRequest);
		return httpRequest;
	}
}	
