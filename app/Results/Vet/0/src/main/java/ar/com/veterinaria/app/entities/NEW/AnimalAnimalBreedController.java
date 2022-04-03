package ar.com.veterinaria.app.entities.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.Animal;
@RestController
@CrossOrigin
public class AnimalAnimalBreedController {

@Autowired
 private AnimalAnimalBreedService animalanimalbreedservice;


@GetMapping
("/AnimalBreed/{id}/Animal/getAnimal")
public Animal getAnimal(@PathVariable(name="id") Integer id){
return animalanimalbreedservice.getAnimal(id);
}


@PutMapping
("/AnimalBreed/{id}/Animal/setAnimal")
public void setAnimal(@PathVariable(name="id") Integer id,@RequestBody Animal animal){
animalanimalbreedservice.setAnimal(id,animal);
}


}