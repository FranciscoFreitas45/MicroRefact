package com.cg.oms.service;

import java.util.List;

import com.cg.oms.exception.AddressNotFoundException;
import com.cg.oms.vo.AddressVo;

/**
 * 
 * @author Muthuraja arumugam
 *
 */
public interface AddressService
{
	/**
	 * Method to be override by the implementing class
	 * 
	 */
	public List<AddressVo> getAllAddress();

	/**
	 * Method to be override by the implementing class
	 * 
	 */
	public AddressVo getAddressById(Long id) throws AddressNotFoundException;

	/**
	 * Method to be override by the implementing class
	 * 
	 */
	public String saveAddress(AddressVo addressVo);

	/**
	 * Method to be override by the implementing class
	 * 
	 */
	public String updateAddress(Long id, AddressVo newAddress) throws AddressNotFoundException;

	/**
	 * Method to be override by the implementing class
	 * 
	 */
	public String deleteAddress(Long id) throws AddressNotFoundException;
}
