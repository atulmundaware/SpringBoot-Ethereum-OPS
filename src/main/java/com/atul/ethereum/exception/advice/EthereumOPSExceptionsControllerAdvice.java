package com.atul.ethereum.exception.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.atul.ethereum.exception.ValidationException;



/**
 * Provide advice to controller to handle all Ethereum OPS related exceptions.
 * 
 * @author <a href="mailto:mundawareatul1@gmail.com">atul.mundaware</a>
 * @version 1.0
 */

@ControllerAdvice
public class EthereumOPSExceptionsControllerAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(EthereumOPSExceptionsControllerAdvice.class);
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorResponse> userAlreadyExistsExceptionHandler(
			ValidationException ex) {
		LOGGER.error(ex.getMessage() ,ex);
		return getErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
	}
	
	
	/**
	 * Method to get error response to return to client.
	 * @param httpStatus
	 *        Status to included in HTTP response.
	 * @param message
	 *        Message to included in HTTP response.
	 * @return
	 *        Response to return as a part of HTTP response.
	 */
	public ResponseEntity<ErrorResponse> getErrorResponse(HttpStatus httpStatus, String message) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(httpStatus.value());
		error.setMessage(message);
		return new ResponseEntity<ErrorResponse>(error, httpStatus);
	}
}