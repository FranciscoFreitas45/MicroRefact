package com.cg.oms.exception;

public class ErrorMessage
{
	private Integer errorCode;

	private String errorInformation;

	public Integer getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(Integer errorCode)
	{
		this.errorCode = errorCode;
	}

	public String getErrorInformation()
	{
		return errorInformation;
	}

	public void setErrorInformation(String errorInformation)
	{
		this.errorInformation = errorInformation;
	}

}
