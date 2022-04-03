package com.softserve.edu.Resources.service;
 import com.softserve.edu.Resources.dto.SearchDTO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.Address;
import org.springframework.validation.BindingResult;
import java.util.List;
public interface AddressService {


public List<Address> findAddresses(SearchDTO searchDTO)
;

public Address getById(long id)
;

public Address updateAddress(Address address)
;

public Address addAddress(Address address)
;

public void deleteAddress(Address address)
;

public List<Address> getAllAddresses()
;

public ValidationErrorDTO validationDTO(BindingResult result)
;

}