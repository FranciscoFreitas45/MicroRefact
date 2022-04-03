package com.cg.hbm.exceptions;

/**
 * 
 * @author Navin Kumar Sharma
 *
 */

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	/**
	 * 
	 * @param errorMessage
	 */

	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public UserNotFoundException() {
		super();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
