package ar.com.veterinaria.app.entities.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.repository.BreedRepository;
import ar.com.veterinaria.app.entities.Breed;
@Service
public class BreedAnimalBreedService {

@Autowired
 private BreedRepository breedrepository;


public void setBreed(Integer id,Breed breed){
breedrepository.setBreed(id,breed);
}


public Breed getBreed(Integer id){
return breedrepository.getBreed(id);
}


}