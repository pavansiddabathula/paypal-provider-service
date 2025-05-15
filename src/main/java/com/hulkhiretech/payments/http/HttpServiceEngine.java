package com.hulkhiretech.payments.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

import com.hulkhiretech.payments.Exceptions.PaypalProviderException;
import com.hulkhiretech.payments.constants.ErrorCodeEnum;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HttpServiceEngine {

    private RestClient restClient;

    public HttpServiceEngine(RestClient.Builder restClientBuilder) {
        restClient = restClientBuilder.build();
        log.info("restClient created|restClient:" + restClient);
    }

    public ResponseEntity<String> makeHttCall(HttpRequest httpRequest) {
        try {
            log.info("Inside HttpServiceEngine class makeHttCall() method is called and the request is : " + httpRequest);

            ResponseEntity<String> responseEntity = restClient.method(httpRequest.getHttpmethod())
                    .uri(httpRequest.getUrl())
                    .headers(httpHeader -> httpHeader.addAll(httpRequest.getHeaders()))
                    .body(httpRequest.getRequestBody())
                    .retrieve()
                    .toEntity(String.class);

            log.info("ResponseEntity inside HttpServiceEngine class : " + responseEntity);
            return responseEntity;

        } catch (HttpServerErrorException | HttpClientErrorException e) {
            log.error("HTTP error occurred: {}", e.getStatusCode(), e);

            // Get the status code
            HttpStatus status = HttpStatus.valueOf(e.getStatusCode().value());

            // Check if it's a 4xx or 5xx error
            if (status.is4xxClientError() || status.is5xxServerError()) {
                log.error("4xx or 5xx error occurred: {}", status);

                // Throw a generic PaypalProviderException for testing purposes
                throw new PaypalProviderException(
                        ErrorCodeEnum.UNABLE_TO_CONNECT_PAYPAL.getCode(),
                        ErrorCodeEnum.UNABLE_TO_CONNECT_PAYPAL.getMessage(),
                        status);
            }

            // Handle other scenarios (if necessary)
            throw new PaypalProviderException(
                    ErrorCodeEnum.GENERIC_ERROR.getCode(),
                    ErrorCodeEnum.GENERIC_ERROR.getMessage(),
                    status);
        } catch (Exception e) {
            log.error("Exception occurred while making HTTP call: {}", e.getMessage(), e);

            // Catch any unexpected exceptions and throw a custom exception
            throw new PaypalProviderException(
                    ErrorCodeEnum.GENERIC_ERROR.getCode(),
                    ErrorCodeEnum.GENERIC_ERROR.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
