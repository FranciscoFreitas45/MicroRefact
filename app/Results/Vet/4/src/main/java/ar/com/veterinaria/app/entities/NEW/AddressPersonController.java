package ar.com.veterinaria.app.entities.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.Address;
@RestController
@CrossOrigin
public class AddressPersonController {

@Autowired
 private AddressPersonService addresspersonservice;


@PutMapping
("/Person/{id}/Address/setAddress")
public void setAddress(@PathVariable(name="id") Integer id,@RequestBody Address address){
addresspersonservice.setAddress(id,address);
}


@GetMapping
("/Person/{id}/Address/getAddress")
public Address getAddress(@PathVariable(name="id") Integer id){
return addresspersonservice.getAddress(id);
}


}