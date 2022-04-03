package io.swagger.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.model.Address;
@RestController
@CrossOrigin
public class AddressMedicController {

@Autowired
 private AddressMedicService addressmedicservice;


@PutMapping
("/Medic/{id}/Address/setAddress")
public void setAddress(@PathVariable(name="id") Long id,@RequestBody List<Address> address){
addressmedicservice.setAddress(id,address);
}


@GetMapping
("/Medic/{id}/Address/getAddress")
public List<Address> getAddress(@PathVariable(name="id") Long id){
return addressmedicservice.getAddress(id);
}


}