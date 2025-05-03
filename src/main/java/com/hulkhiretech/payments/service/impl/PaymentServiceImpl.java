package com.hulkhiretech.payments.service.impl;

import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.pojo.CreateOrderReq;
import com.hulkhiretech.payments.service.TokenService;
import com.hulkhiretech.payments.service.interfaces.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
	private final TokenService tokenService;
	
	@Override
	public String createOrder(CreateOrderReq req) {
	/*
		1.call get AccessToken() API
		2.Call create order(_createOrder) API
		3.handle response Success or failure
		4.return response to controller
	*/
	 log.info("createOrder called|req:{}", req);
	 String accessToken = tokenService.getToken();
		
		return "returnig from service impl| accessToken:" + accessToken;
	}

}
