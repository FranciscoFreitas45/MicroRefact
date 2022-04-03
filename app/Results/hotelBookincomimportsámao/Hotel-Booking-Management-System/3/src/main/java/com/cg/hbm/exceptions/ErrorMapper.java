package com.cg.hbm.exceptions;


public class ErrorMapper {
	
	private int errorCode;
	private String errorMessage;
	/**
	 * 
	 * @return int
	 */
	
	public int getErrorCode() {
		return errorCode;
	}
	/**
	 * 
	 * @param errorCode
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * 
	 * @return String
	 */
	public String getErrorMessage() {
		return errorMessage;
	
	}
	/**
	 * 
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
