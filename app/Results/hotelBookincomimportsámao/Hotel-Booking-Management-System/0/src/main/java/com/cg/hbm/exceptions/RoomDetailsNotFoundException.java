package com.cg.hbm.exceptions;
/**
 * 
 * @author Mahendra
 *
 */

public class RoomDetailsNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	/**
	 * 
	 * @param errorMessage
	 */

	public 	RoomDetailsNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public RoomDetailsNotFoundException() {
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
