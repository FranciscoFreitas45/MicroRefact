package com.cg.oms.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cg.oms.model.Address;
import com.cg.oms.vo.AddressVo;

/**
 * This function is used to convert the address object to the addressVo Object
 * and vise versa this is used for getting and returning the object for security
 * purpose.
 * 
 * @author Inam - PC
 *
 */

@Component
public class AddressConverter
{

	/**
	 * This method converts model object to value object
	 * 
	 * @param address
	 * @return
	 */
	public AddressVo modelToVo(Address address)
	{
		AddressVo addressVo = new AddressVo();
		addressVo.setAddressId(address.getAddressId());
		addressVo.setArea(address.getArea());
		addressVo.setCity(address.getCity());
		addressVo.setFlatNo(address.getFlatNo());
		addressVo.setPinCode(address.getPinCode());
		addressVo.setState(address.getState());
		addressVo.setStreetName(address.getStreetName());
		return addressVo;
	}

	/**
	 * This method converts model object to value object
	 * 
	 * @param address
	 * @return
	 */
	public List<AddressVo> modelToVo(List<Address> address)
	{
		return address.stream().map(x -> modelToVo(x)).collect(Collectors.toList());
	}

	/**
	 * This method converts value object to model object
	 * 
	 * @param addressVo
	 * @return
	 */
	public Address voToModel(AddressVo addressVo)
	{
		Address address = new Address();
		address.setAddressId(addressVo.getAddressId());
		address.setArea(addressVo.getArea());
		address.setCity(addressVo.getCity());
		address.setFlatNo(addressVo.getFlatNo());
		address.setPinCode(addressVo.getPinCode());
		address.setState(addressVo.getState());
		address.setStreetName(addressVo.getStreetName());
		return address;
	}

	/**
	 * This method converts value object to model object
	 * 
	 * @param addressVo
	 * @return
	 */
	public List<Address> voToModel(List<AddressVo> addressVo)
	{
		return addressVo.stream().map(x -> voToModel(x)).collect(Collectors.toList());
	}

}
