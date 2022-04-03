package com.cg.hbm.exceptions;
/**
 * 
 * @author Keerthi
 *
 */

public class PaymentNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	/**
	 * 
	 * @param errorMessage
	 */

	public 	PaymentNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public PaymentNotFoundException() {
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
