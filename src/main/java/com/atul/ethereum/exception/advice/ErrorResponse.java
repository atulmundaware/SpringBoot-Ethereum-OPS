package com.atul.ethereum.exception.advice;
/**
 * Error response to return in case of exception.
 * 
 * @author <a href="mailto:mundawareatul1@gmail.com">atul.mundaware</a>
 * @version 1.0
 */

public class ErrorResponse {

	private int errorCode;
	private String message;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}