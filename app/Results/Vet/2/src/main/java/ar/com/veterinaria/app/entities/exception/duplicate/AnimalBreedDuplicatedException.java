package ar.com.veterinaria.app.entities.exception.duplicate;
 import ar.com.veterinaria.app.entities.AnimalBreed;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AnimalBreedDuplicatedException extends RuntimeException{

 private  long serialVersionUID;

public AnimalBreedDuplicatedException(int id) {
    super("The AnimalBreed with id '" + id + "' already exists.");
}public AnimalBreedDuplicatedException(AnimalBreed animalBreed) {
    super("\n\tThe AnimalBreed \n\tAnimal: '" + animalBreed.getAnimal().getName() + "' && Breed: '" + animalBreed.getBreed().getBreed() + "'  already exists.");
}
}