package com.cg.oms.exception;


/**
 * 
 * This exception is thrown or called if the requested order Id is not found..
 * we called this method in the Service Method
 * and return if the condition not met.
 * @author Inam - PC
 *
 */


public class RoleNotFoundException extends Exception
{
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 5563313999505628095L;

	
	/**
	 * parameterized Constructor
	 * @param message
	 */
	public RoleNotFoundException(String message)
	{
		super(message);
	}
}
