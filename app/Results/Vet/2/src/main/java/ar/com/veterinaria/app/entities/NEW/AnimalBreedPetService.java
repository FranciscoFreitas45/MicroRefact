package ar.com.veterinaria.app.entities.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.repository.AnimalBreedRepository;
import ar.com.veterinaria.app.entities.AnimalBreed;
@Service
public class AnimalBreedPetService {

@Autowired
 private AnimalBreedRepository animalbreedrepository;


public void setAnimalBreed(Integer id,AnimalBreed animalBreed){
animalbreedrepository.setAnimalBreed(id,animalBreed);
}


public AnimalBreed getAnimalBreed(Integer id){
return animalbreedrepository.getAnimalBreed(id);
}


}