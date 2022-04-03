package ar.com.veterinaria.app.entities.exception.validationLength;
 import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ar.com.veterinaria.app.entities.AnimalBreed;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AnimalBreedValidationLengthDataException extends RuntimeException{

 private  long serialVersionUID;

public AnimalBreedValidationLengthDataException(AnimalBreed animalBreed) {
    super("AnimalBreed validation lenght: \n Length for  \n\t Animal is: " + animalBreed.getAnimal().getName().length() + " for name: '" + animalBreed.getAnimal().getName() + "', \n\t it must not be over 70 characters \n Length for \n\tBreed is " + animalBreed.getBreed().getBreed().length() + " for name '" + animalBreed.getBreed().getBreed() + "',\n\t it must not be over 50 characters \n");
}
}