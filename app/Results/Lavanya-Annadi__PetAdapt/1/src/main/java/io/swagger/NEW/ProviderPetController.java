package io.swagger.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.model.Provider;
@RestController
@CrossOrigin
public class ProviderPetController {

@Autowired
 private ProviderPetService providerpetservice;


@PutMapping
("/Pet/{id}/Provider/setProvider")
public void setProvider(@PathVariable(name="id") Long id,@RequestBody Provider provider){
providerpetservice.setProvider(id,provider);
}


@GetMapping
("/Pet/{id}/Provider/getProvider")
public Provider getProvider(@PathVariable(name="id") Long id){
return providerpetservice.getProvider(id);
}


}