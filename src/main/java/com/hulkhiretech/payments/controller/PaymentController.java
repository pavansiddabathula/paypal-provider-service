package com.hulkhiretech.payments.controller;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hulkhiretech.payments.service.interfaces.PaymentService;
import com.hulkhiretech.payments.pojo.CreateOrderReq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/paypal/order")

public class PaymentController {
	
	private final PaymentService paymentService;
	
	@PostMapping()
	public String createOrder(
			@RequestBody CreateOrderReq req) {
		
		log.info("createOrderReq:{}", req);
		String response = paymentService.createOrder(req);
		
		return "createOrder returning "
				+ "createOrderReq:" + req 
				+ "\nresponse:" + response;
	}
    
   
    
  
    
}
