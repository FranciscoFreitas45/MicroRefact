package ar.com.veterinaria.app.entities.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.repository.AnimalRepository;
import ar.com.veterinaria.app.entities.Animal;
@Service
public class AnimalAnimalBreedService {

@Autowired
 private AnimalRepository animalrepository;


public Animal getAnimal(Integer id){
return animalrepository.getAnimal(id);
}


public void setAnimal(Integer id,Animal animal){
animalrepository.setAnimal(id,animal);
}


}