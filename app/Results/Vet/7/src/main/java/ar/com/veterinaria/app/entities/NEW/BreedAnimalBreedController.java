package ar.com.veterinaria.app.entities.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.Breed;
@RestController
@CrossOrigin
public class BreedAnimalBreedController {

@Autowired
 private BreedAnimalBreedService breedanimalbreedservice;


@PutMapping
("/AnimalBreed/{id}/Breed/setBreed")
public void setBreed(@PathVariable(name="id") Integer id,@RequestBody Breed breed){
breedanimalbreedservice.setBreed(id,breed);
}


@GetMapping
("/AnimalBreed/{id}/Breed/getBreed")
public Breed getBreed(@PathVariable(name="id") Integer id){
return breedanimalbreedservice.getBreed(id);
}


}