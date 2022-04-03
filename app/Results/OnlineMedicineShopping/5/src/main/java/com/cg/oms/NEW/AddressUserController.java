package com.cg.oms.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.model.Address;
@RestController
@CrossOrigin
public class AddressUserController {

@Autowired
 private AddressUserService addressuserservice;


@GetMapping
("/User/{id}/Address/getUserAddress")
public Address getUserAddress(@PathVariable(name="id") Integer addressId){
return addressuserservice.getUserAddress(addressId);
}


@PutMapping
("/User/{id}/Address/setUserAddress")
public void setUserAddress(@PathVariable(name="id") Integer addressId,@RequestBody Address userAddress){
addressuserservice.setUserAddress(addressId,userAddress);
}


}