package com.cg.oms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oms.converter.AddressConverter;
import com.cg.oms.exception.AddressNotFoundException;
import com.cg.oms.model.Address;
import com.cg.oms.repository.AddressRepository;
import com.cg.oms.vo.AddressVo;

/**
 * Address Serivce
 * 
 * @author Inam - PC
 *
 */

@Service
public class AddressServiceImpl implements AddressService
{
	public static final String EXCEPTION_MESSAGE = "No Address found with this id ";
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	private AddressConverter addressConverter;

	/**
	 * To List all the address called from the controller class and send back to the
	 * Controller
	 * 
	 * @return
	 */
	@Override
	public List<AddressVo> getAllAddress()
	{
		List<Address> address = addressRepository.findAll();
		return addressConverter.modelToVo(address);
	}

	/**
	 * Get and retrieve a specific Address based on the given id else throws
	 * AddressNotFound Exception
	 * 
	 * @param id
	 * @return
	 * @throws AddressNotFoundException
	 */
	@Override
	public AddressVo getAddressById(Long id) throws AddressNotFoundException
	{
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new AddressNotFoundException(EXCEPTION_MESSAGE + id));
		return addressConverter.modelToVo(address);

	}

	/**
	 * To store the address
	 * 
	 * @param addressVo
	 * @return
	 */
	@Override
	public String saveAddress(AddressVo addressVo)
	{
		Address address = addressConverter.voToModel(addressVo);
		address = addressRepository.save(address);
		AddressVo savedVo = addressConverter.modelToVo(address);
		return "Registered SuccessFully!!! " + savedVo.toString();
	}

	/**
	 * To update the adddress based on the given id and object
	 * 
	 * @param id
	 * @param newAddress
	 * @return
	 * @throws AddressNotFoundException
	 */
	@Override
	public String updateAddress(Long id, AddressVo newAddress) throws AddressNotFoundException
	{
		Address oldAddress = addressRepository.findById(id)
				.orElseThrow(() -> new AddressNotFoundException(EXCEPTION_MESSAGE + id));

		oldAddress.setFlatNo(newAddress.getFlatNo());
		oldAddress.setStreetName(newAddress.getStreetName());
		oldAddress.setArea(newAddress.getArea());
		oldAddress.setCity(newAddress.getCity());
		oldAddress.setState(newAddress.getState());
		oldAddress.setPinCode(newAddress.getPinCode());
		Address updatedAddress = addressRepository.save(oldAddress);
		AddressVo savedVo = addressConverter.modelToVo(updatedAddress);
		return "Updated Successfully" + savedVo.toString();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws AddressNotFoundException
	 */
	@Override
	public String deleteAddress(Long id) throws AddressNotFoundException
	{
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new AddressNotFoundException("no Address found with this id " + id));
		addressRepository.delete(address);
		return "Record Deleted Successfully!!";
	}

}
