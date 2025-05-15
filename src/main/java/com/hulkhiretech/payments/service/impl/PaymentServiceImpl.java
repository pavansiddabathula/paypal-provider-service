package com.hulkhiretech.payments.service.impl;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.hulkhiretech.payments.http.HttpRequest;
import com.hulkhiretech.payments.http.HttpServiceEngine;
import com.hulkhiretech.payments.paypal.res.CreatOrderRes;
import com.hulkhiretech.payments.paypal.res.Link;
import com.hulkhiretech.payments.pojo.CreateOrderReq;
import com.hulkhiretech.payments.pojo.OrderRes;
import com.hulkhiretech.payments.service.TokenService;
import com.hulkhiretech.payments.service.helper.CaptureOrderHelper;
import com.hulkhiretech.payments.service.helper.CreateOrderHelper;
import com.hulkhiretech.payments.service.helper.GetOrderHelper;
import com.hulkhiretech.payments.service.interfaces.PaymentService;
import com.hulkhiretech.payments.util.JsonUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
	private final HttpServiceEngine httpServiceEngine;

	private CreateOrderHelper createOrderHelper;
	private GetOrderHelper getOrderHelper;
	private CaptureOrderHelper captureOrderHelper;
	private final TokenService tokenService;
	public String accessToken = null;

	@Override
	public OrderRes createOrder(CreateOrderReq req) {

		log.info("createOrder called|req:{}", req);
		if (accessToken == null) {
			log.error("Access token is null");
			accessToken = tokenService.getToken();
			log.info("Access token is null, generated new access token: " + accessToken);
		}
		
		
		createOrderHelper = new CreateOrderHelper();

		HttpRequest httpRequest = createOrderHelper.prepareHttpRequestForCreateOrder(req, accessToken);

		ResponseEntity<String> orderResponse = httpServiceEngine.makeHttCall(httpRequest);
		String responseBody = orderResponse.getBody();
		log.info("ResponseBody inside TokenService class : " + responseBody);

		if (responseBody == null) {
			log.error("Response body is null");
			throw new RuntimeException("Response body is null");
		}

		CreatOrderRes resObj = JsonUtil.fromJson(responseBody, CreatOrderRes.class);
		log.info("resObj:" + resObj);

		OrderRes orderRes = new OrderRes();
		orderRes.setOrderId(resObj.getId());
		orderRes.setPaypalStatus(resObj.getStatus());

		Optional<String> opRedirectUrl = resObj.getLinks().stream()
				.filter(link -> "payer-action".equalsIgnoreCase(link.getRel())).map(Link::getHref).findFirst();

		orderRes.setRedirectUrl(opRedirectUrl.orElse(null));

		log.info("orderRes:{}", orderRes);

		return orderRes;

	}

	@Override
	public OrderRes getOrder(String orderId) {
		log.info("getOrder called|orderId:{}", orderId);

		if (accessToken == null) {
			log.error("Access token is null");
			
			accessToken = tokenService.getToken();
			log.info("Access token is null, generated new access token: " + accessToken);
		}
		log.info("its executing here before calling getorderhelper class");
		getOrderHelper = new GetOrderHelper();
		accessToken ="aaaaahghg";
		
		HttpRequest httpRequest = getOrderHelper.prepareHttpRequestForGetOrder(orderId, accessToken);

		ResponseEntity<String> orderResponse = httpServiceEngine.makeHttCall(httpRequest);
		String responseBody = orderResponse.getBody();
		log.info("ResponseBody inside TokenService class : " + responseBody);

		if (responseBody == null) {
			log.error("Response body is null");
			throw new RuntimeException("Response body is null");
		}

		CreatOrderRes resObj = JsonUtil.fromJson(responseBody, CreatOrderRes.class);
		log.info("resObj:" + resObj);

		OrderRes orderRes = new OrderRes();
		orderRes.setOrderId(resObj.getId());
		orderRes.setPaypalStatus(resObj.getStatus());

		Optional<String> opRedirectUrl = resObj.getLinks().stream()
				.filter(link -> "payer-action".equalsIgnoreCase(link.getRel())).map(Link::getHref).findFirst();

		orderRes.setRedirectUrl(opRedirectUrl.orElse(null));

		log.info("orderRes:{}", orderRes);

		return orderRes;

	}
	@Override
	public OrderRes captureOrder(String orderId) {
		log.info("captureOrder called|orderId:{}", orderId);
		if (accessToken == null) {
			log.error("Access token is null");
			accessToken = tokenService.getToken();
			log.info("Access token is null, generated new access token: " + accessToken);
		}
      
		captureOrderHelper = new CaptureOrderHelper();
		
		HttpRequest httpRequest = captureOrderHelper.prepareHttpRequestForCaptureOrder(orderId, accessToken);

		ResponseEntity<String> orderResponse = httpServiceEngine.makeHttCall(httpRequest);
		String responseBody = orderResponse.getBody();
		log.info("ResponseBody inside TokenService class : " + responseBody);

		if (responseBody == null) {
			log.error("Response body is null");
			throw new RuntimeException("Response body is null");
		}

		CreatOrderRes resObj = JsonUtil.fromJson(responseBody, CreatOrderRes.class);
		log.info("resObj:" + resObj);

		OrderRes orderRes = new OrderRes();
		orderRes.setOrderId(resObj.getId());
		orderRes.setPaypalStatus(resObj.getStatus());

		Optional<String> opRedirectUrl = resObj.getLinks().stream()
				.filter(link -> "payer-action".equalsIgnoreCase(link.getRel())).map(Link::getHref).findFirst();

		orderRes.setRedirectUrl(opRedirectUrl.orElse(null));

		log.info("orderRes:{}", orderRes);

		return orderRes;

	}

}
