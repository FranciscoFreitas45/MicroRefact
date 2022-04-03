package com.softserve.edu.Resources.service.impl;
 import com.softserve.edu.Resources.dao.AddressDAO;
import com.softserve.edu.Resources.dto.SearchDTO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.service.AddressService;
import com.softserve.edu.Resources.util.QueryBuilder;
import com.softserve.edu.Resources.util.ValidationDTOUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
@Transactional
public class AddressServiceImpl implements AddressService{

 static  Logger logger;

@Autowired
 private AddressDAO addressDAO;

@Autowired
 private  QueryBuilder queryBuilder;

@Autowired
 private  ValidationDTOUtility validationUtility;


@Override
public List<Address> findAddresses(SearchDTO searchDTO){
    // check if all fields and values are empty
    List<Map.Entry<String, String>> nonEmptyValues = searchDTO.getFieldsAndValues().entrySet().stream().filter(stringStringEntry -> !stringStringEntry.getValue().isEmpty()).collect(Collectors.toList());
    // if so, return empty list
    if (nonEmptyValues.isEmpty()) {
        return new ArrayList<>();
    }
    return addressDAO.findAddresses(searchDTO);
}


@Override
public Address getById(long id){
    logger.trace("Retrieving address:" + id);
    return addressDAO.findById(id).orElse(new Address());
}


@Override
public Address updateAddress(Address address){
    logger.trace("Updating address:" + address);
    return addressDAO.makePersistent(address);
}


@Override
public Address addAddress(Address address){
    logger.trace("Saving address:" + address);
    return addressDAO.makePersistent(address);
}


@Override
public void deleteAddress(Address address){
    logger.trace("Deleting address:" + address);
    addressDAO.deleteAddress(address);
}


@Override
public List<Address> getAllAddresses(){
    logger.trace("Retrieving all addresses");
    return addressDAO.findAll();
}


@Override
public ValidationErrorDTO validationDTO(BindingResult result){
    logger.trace("Validating address with errors in fields:" + result.getFieldErrors());
    return validationUtility.getErrorDTO(result);
}


}