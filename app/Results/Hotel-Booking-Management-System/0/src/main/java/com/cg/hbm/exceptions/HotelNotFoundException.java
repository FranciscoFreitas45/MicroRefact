package com.cg.hbm.exceptions;

/**
 * 
 * @author Harshitha
 *
 */
public class HotelNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	/**
	 * 
	 * @param errorMessage
	 */

	public HotelNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public HotelNotFoundException() {
		super();
	}
	/**
	 * 
	 * @return String
	 */

	public String getErrorMessage() {
		return errorMessage;
	}
}
