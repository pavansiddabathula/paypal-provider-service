package com.hulkhiretech.payments.http;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HttpServiceEngine {

	private RestClient restClient;
	public HttpServiceEngine(RestClient.Builder restClientBuilder) {
		restClient = restClientBuilder.build();
		log.info("restClient created|restClient:" + restClient);
		// Constructor logic if needed
	}
	public ResponseEntity<String> makeHttCall( HttpRequest httpRequest){
		
		log.info("Inside HttpServiceEngine class makeHttCall() method is called and the request is : " + httpRequest);
		
		ResponseEntity<String> responseEntity = restClient.method(httpRequest.getHttpmethod())
		.uri(httpRequest.getUrl())
		.headers(httpHeader -> httpHeader.addAll(httpRequest.getHeaders()))
		.body(httpRequest.getRequestBody())
		.retrieve()
		.toEntity(String.class);//lambda expression function based coding 
		
		log.info("ResponseEntity inside HttpServiceEngine class : " + responseEntity);
		return responseEntity;
		
	}
	

	


}
