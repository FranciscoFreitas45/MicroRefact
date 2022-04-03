package com.cg.hbm.exceptions;
/**
 * 
 * @author KV Prasad
 *
 */

public class TransactionNotFoundException {
	
private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	/**
	 * 
	 * @param errorMessage
	 */

	public TransactionNotFoundException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public TransactionNotFoundException() {
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



