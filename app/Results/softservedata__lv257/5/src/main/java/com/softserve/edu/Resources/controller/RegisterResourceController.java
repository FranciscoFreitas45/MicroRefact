package com.softserve.edu.Resources.controller;
 import com.fasterxml.jackson.core.JsonProcessingException;
import com.softserve.edu.Resources.dto;
import com.softserve.edu.Resources.entity;
import com.softserve.edu.Resources.service.AddressService;
import com.softserve.edu.Resources.service.OwnerService;
import com.softserve.edu.Resources.service.ResourceService;
import com.softserve.edu.Resources.service.ResourceTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import com.softserve.edu.Resources.Interface.ResourceTypeService;
@Controller
@RequestMapping("/resources")
public class RegisterResourceController {

 static  Logger logger;

@Autowired
 private AddressService addressService;

@Autowired
 private OwnerService ownerService;

@Autowired
 private ResourceTypeService resourceTypeService;

@Autowired
 private ResourceService resourceService;


@ResponseBody
@RequestMapping(value = "/owner/search", method = RequestMethod.POST)
public ResponseEntity<?> searchOwner(SearchDTO searchDTO){
    logger.debug("Searching owner with values: " + searchDTO.getFieldsAndValues().values());
    List<Owner> owners = ownerService.findOwners(searchDTO);
    if (owners.isEmpty()) {
        logger.warn("Owner was not found!");
        return new ResponseEntity<>(new FieldErrorDTO("errors", "Nothing was found. Please, try again."), HttpStatus.BAD_REQUEST);
    }
    List<OwnerDTO> ownerDTOS = ownerService.fromOwnerToOwnerDto(owners);
    return new ResponseEntity<>(ownerDTOS, HttpStatus.OK);
}


@ResponseBody
@RequestMapping(value = "/owner", method = RequestMethod.POST)
public ResponseEntity<?> saveResourceOwnerWithAddress(Owner owner,BindingResult result){
    logger.info("Saving owner: " + owner);
    System.out.println("Saving owner: " + owner);
    System.out.println("Saving owner's address: " + owner.getAddress());
    ValidationErrorDTO validationErrorDTO;
    if (result.hasErrors()) {
        logger.warn("Errors in the owner object!");
        validationErrorDTO = ownerService.getValidationDTO(result);
        System.out.println(validationErrorDTO.getFieldErrors());
        return new ResponseEntity<>(validationErrorDTO, HttpStatus.BAD_REQUEST);
    }
    Owner savedOwner = ownerService.addOwner(owner);
    logger.debug("Saved owner: " + savedOwner);
    logger.debug("Saved owner id: " + savedOwner.getId());
    logger.debug("Saved owner's address: " + savedOwner.getAddress());
    List<OwnerDTO> ownerDTOS = ownerService.fromOwnerToOwnerDto(Collections.singletonList(savedOwner));
    return new ResponseEntity<>(ownerDTOS.get(0), HttpStatus.OK);
}


@ResponseBody
@RequestMapping(value = "/address/search", method = RequestMethod.POST)
public ResponseEntity<?> searchAddress(SearchDTO searchDTO){
    logger.debug("Searching address with values: " + searchDTO.getFieldsAndValues().values());
    List<Address> addresses = addressService.findAddresses(searchDTO);
    if (addresses.isEmpty()) {
        logger.warn("Address was not found!");
        return new ResponseEntity<>(new FieldErrorDTO("errors", "Nothing was found. Please, try again."), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(addresses, HttpStatus.OK);
}


@RequestMapping(value = "/registration", method = RequestMethod.GET)
public String registerResourceGetPage(){
    return "registerResource";
}


@ResponseBody
@RequestMapping(value = "/address", method = RequestMethod.POST)
public ResponseEntity<?> saveResourceAddress(Address address,BindingResult result){
    logger.info("Saving address: " + address);
    if (result.hasErrors()) {
        logger.warn("Errors in address object!");
        return new ResponseEntity<>(addressService.validationDTO(result), HttpStatus.BAD_REQUEST);
    }
    Address savedAddress = addressService.addAddress(address);
    logger.debug("Saved address in the bd: " + savedAddress);
    return new ResponseEntity<>(savedAddress, HttpStatus.OK);
}


@RequestMapping(value = "/registration", method = RequestMethod.POST)
@ResponseBody
public ResponseEntity<?> registerResource(ResourceImplDTO resourceImplDTO,HttpSession session) throws Exception{
    ValidationErrorDTO validationErrorDTO = resourceService.validateResourceImpl(resourceImplDTO);
    if (validationErrorDTO.getFieldErrors().isEmpty()) {
        ValidationErrorDTO validationErrorDTOTwo = resourceService.validateResourceImplUniqueFields(resourceImplDTO);
        System.out.println(validationErrorDTO);
        if (!validationErrorDTOTwo.getFieldErrors().isEmpty()) {
            return new ResponseEntity<>(validationErrorDTOTwo, HttpStatus.BAD_REQUEST);
        } else {
            long addressId = resourceImplDTO.getAddressId();
            Address resourceAddress = addressService.getById(addressId);
            ResourceType resourceTypeWithProperties = resourceTypeService.findWithPropertiesByID(resourceImplDTO.getResourceTypeId());
            Resource resource = new Resource();
            resource.setAddress(resourceAddress);
            // insert Resource, ResourceOwnings records and concrete resource impl table
            resourceService.addResource(resource, resourceImplDTO);
            // use this dto just because I don't want to make another dto with same two String fields
            FieldErrorDTO redirectUrl = new FieldErrorDTO("redirect", "registration");
            session.setAttribute("resourceRegistered", true);
            return new ResponseEntity<>(redirectUrl, HttpStatus.OK);
        }
    } else {
        return new ResponseEntity<>(validationErrorDTO, HttpStatus.BAD_REQUEST);
    }
}


@ResponseBody
@RequestMapping(value = "/address/delete", method = RequestMethod.DELETE)
public void deleteResourceAddress(Address address){
    logger.debug("Deleting address: " + address);
    addressService.deleteAddress(address);
    logger.warn("Address deleted from the db" + address);
}


@ResponseBody
@RequestMapping(value = "/owner/{id}/delete", method = RequestMethod.DELETE)
public void deleteOwner(long id) throws JsonProcessingException{
    logger.warn("Deleting owner with id:" + id);
    ownerService.deleteOwnerById(id);
}


@ExceptionHandler(DataIntegrityViolationException.class)
@ResponseStatus(HttpStatus.FORBIDDEN)
public void handleDataIntegrityViolationExceptionException(DataIntegrityViolationException e){
    logger.error("integrity violation", e);
    System.out.println(e.getMessage());
}


@ExceptionHandler(Exception.class)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public void handleGeneralException(DataIntegrityViolationException e){
    logger.error("some exception", e);
    System.out.println(e.getMessage());
}


@ResponseBody
@RequestMapping(value = "/api/{resourceTypeId}", method = RequestMethod.GET)
public List<ConstrainedProperty> searchProperties(String resourceTypeId){
    System.out.println("resource type id to search properties: " + resourceTypeId);
    ResourceType withPropertiesByID = resourceTypeService.findWithPropertiesByID(Long.parseLong(resourceTypeId));
    Set<ConstrainedProperty> properties = withPropertiesByID.getProperties();
    return new ArrayList<>(properties);
}


}