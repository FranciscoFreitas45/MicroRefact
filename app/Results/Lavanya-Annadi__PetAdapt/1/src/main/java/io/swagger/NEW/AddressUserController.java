package io.swagger.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.model.Address;
@RestController
@CrossOrigin
public class AddressUserController {

@Autowired
 private AddressUserService addressuserservice;


@GetMapping
("/User/{id}/Address/getAddress")
public List<Address> getAddress(@PathVariable(name="id") Long id){
return addressuserservice.getAddress(id);
}


@PutMapping
("/User/{id}/Address/setAddress")
public void setAddress(@PathVariable(name="id") Long id,@RequestBody List<Address> address){
addressuserservice.setAddress(id,address);
}


}