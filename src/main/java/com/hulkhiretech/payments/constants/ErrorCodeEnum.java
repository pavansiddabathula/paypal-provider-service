package com.hulkhiretech.payments.constants;


import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
  GENERIC_ERROR("40000", "server time out "),
  PAY("40002", "Paypal request timed out"),
  UNAUTHORIZED_ACCESS("AUTH_001", "Unauthorized access. Please check your credentials or permissions."),
  UNABLE_TO_CONNECT_PAYPAL("40004", "Paypal service is currently unavailable. Please try again later"),
  // … add more as needed
  ;
  private final String code;
  private final String message;
  ErrorCodeEnum(String code, String message) { 
	  this.code = code; 
	  this.message = message; 

  }
} 