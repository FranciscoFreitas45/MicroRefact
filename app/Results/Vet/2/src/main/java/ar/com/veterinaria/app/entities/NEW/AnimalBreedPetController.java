package ar.com.veterinaria.app.entities.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.AnimalBreed;
@RestController
@CrossOrigin
public class AnimalBreedPetController {

@Autowired
 private AnimalBreedPetService animalbreedpetservice;


@PutMapping
("/Pet/{id}/AnimalBreed/setAnimalBreed")
public void setAnimalBreed(@PathVariable(name="id") Integer id,@RequestBody AnimalBreed animalBreed){
animalbreedpetservice.setAnimalBreed(id,animalBreed);
}


@GetMapping
("/Pet/{id}/AnimalBreed/getAnimalBreed")
public AnimalBreed getAnimalBreed(@PathVariable(name="id") Integer id){
return animalbreedpetservice.getAnimalBreed(id);
}


}