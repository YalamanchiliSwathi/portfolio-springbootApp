package com.fed1.portfolio.portfolio.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	
	private String message;
	private int statusCode;
	

}
