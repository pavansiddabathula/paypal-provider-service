package com.hulkhiretech.payments.paypal.res;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true) 

@Data
public class Link {

    private String href;
	private String rel;
	private String method;

}
