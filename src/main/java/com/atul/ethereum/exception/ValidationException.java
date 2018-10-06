package com.atul.ethereum.exception;
/**
 * The class {@code ValidationException} indicates condition that a validation for 
 * provided parameters failed.
 * 
 * @author <a href="mailto:mundawareatul1@gmail.com">atul.mundaware</a>
 * @version 1.0
 */
public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ValidationException(String errorMessage) {
		super("Validation Failed :'" + errorMessage + "'");
	}

}