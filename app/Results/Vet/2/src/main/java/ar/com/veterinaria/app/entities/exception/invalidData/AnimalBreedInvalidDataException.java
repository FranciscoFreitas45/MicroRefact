package ar.com.veterinaria.app.entities.exception.invalidData;
 import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ar.com.veterinaria.app.entities.AnimalBreed;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AnimalBreedInvalidDataException extends RuntimeException{

 private  long serialVersionUID;

public AnimalBreedInvalidDataException(AnimalBreed animalBreed) {
    super("Invalid Name: \n Animal: " + animalBreed.getAnimal().getName() + ", Breed: " + animalBreed.getBreed().getBreed() + ". It must be capital or lower letters and whitespace\n");
}
}