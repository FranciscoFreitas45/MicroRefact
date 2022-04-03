package com.cg.oms.exception;

/**
 * Class OrderNotFoundException extends {@link java.lang.Exception}
 * @author Inam - PC
 *
 */
public class OrderNotFoundException extends Exception
{

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -3721678287968134579L;

	
	/**
	 * Parameterized Constructor
	 * @param message
	 */
	public OrderNotFoundException(String message)
	{
		super(message);
	}

}
