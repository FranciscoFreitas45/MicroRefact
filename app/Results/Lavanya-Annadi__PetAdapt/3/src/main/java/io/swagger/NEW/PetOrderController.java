package io.swagger.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.model.Pet;
@RestController
@CrossOrigin
public class PetOrderController {

@Autowired
 private PetOrderService petorderservice;


@GetMapping
("/Order/{id}/Pet/getPet")
public Pet getPet(@PathVariable(name="id") Long id){
return petorderservice.getPet(id);
}


@PutMapping
("/Order/{id}/Pet/setPet")
public void setPet(@PathVariable(name="id") Long id,@RequestBody Pet pet){
petorderservice.setPet(id,pet);
}


}