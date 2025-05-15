package com.hulkhiretech.payments.service.helper;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.constants.Constants;
import com.hulkhiretech.payments.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class GetOrderHelper {
	
	
	

	

	public HttpRequest prepareHttpRequestForGetOrder(String orderId, String accessToken) {
		/*
		 * 1.call get AccessToken() API 2.Call Showorder(ShowOrder) API 3.handle
		 * response Success or failure 4.return response to controller
		 * 
		 */
		// header object creation
		String getOrderUrl = Constants.GetOrderUrl.replace("{orderId}", orderId);
		
		log.info("GetOrderHelper:prepareHttpRequestForGetOrder|getOrderUrl:{}", getOrderUrl);
		HttpHeaders headersobj = new HttpHeaders();
		headersobj.setBearerAuth(accessToken);
		log.info("GetOrderHelper:prepareHttpRequestForGetOrder|headersobj:{}", headersobj);
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(getOrderUrl);
		httpRequest.setHttpmethod(HttpMethod.GET);
		httpRequest.setHeaders(headersobj);
		httpRequest.setRequestBody(Constants.EMPTYSTRING);
	    log.info("GetOrderHelper:prepareHttpRequestForGetOrder|httpRequest:{}", httpRequest);
		
		return httpRequest;
	}
}
