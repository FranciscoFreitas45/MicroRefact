package com.cg.oms.exception;

/**
 * Class UserNotFoundException extends {@link java.lang.Exception}
 * 
 * @author Arivazhagan
 */
public class UserNotFoundException extends Exception
{
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 501679211894389290L;

	/**
	 * Parameterized constructor
	 * 
	 * @param message
	 */
	public UserNotFoundException(String message)
	{
		super(message);
	}
}
