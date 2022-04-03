package com.cg.oms.exception;


/**
 * Class MedicineNotFoundException extends {@link java.lang.Exception}
 * @author Inam - PC
 *
 */
public class MedicineNotFoundException extends Exception
{

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -6028773944759320443L;

	
	/**
	 * Paramterized Constructor
	 * @param message
	 */
	public MedicineNotFoundException(String message)
	{
		super(message);
	}

}
