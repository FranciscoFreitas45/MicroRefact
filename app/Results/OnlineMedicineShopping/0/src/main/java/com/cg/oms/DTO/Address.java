package com.cg.oms.DTO;


/**
 * AddressEntity class
 * 
 * @author
 *
 */


public class Address
{
	/**
	 * creating instance variables for the class AddressEntity
	 */

	private Integer addressId;
	private String flatNo;
	private String streetName;
	private String area;
	private String city;
	private String state;
	private Integer pinCode;

	/**
	 * creating default constructors
	 */
	public Address()
	{
		super();
	}

	/**
	 * creating getters and setters for AdminEntity class
	 */

	public String getFlatNo()
	{
		return flatNo;
	}

	public Integer getAddressId()
	{
		return addressId;
	}

	public void setAddressId(Integer addressId)
	{
		this.addressId = addressId;
	}

	public void setFlatNo(String flatNo)
	{
		this.flatNo = flatNo;
	}

	public String getStreetName()
	{
		return streetName;
	}

	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public Integer getPinCode()
	{
		return pinCode;
	}

	public void setPinCode(Integer pinCode)
	{
		this.pinCode = pinCode;
	}

	/**
	 * Implementing and overriding toString to retrieve all values in the instance
	 * of Address Class
	 */
	@Override
	public String toString()
	{
		return "Address [ " + flatNo + "\t" + streetName + "\t" + area + "\t" + city + "\t" + state + "\t" + pinCode
				+ "\t" + "\t ]";
	}
}