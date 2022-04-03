package ar.com.veterinaria.app.entities.exception.notFound;
 import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ar.com.veterinaria.app.entities.Breed;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BreedNotFoundException extends RuntimeException{

 private  long serialVersionUID;

public BreedNotFoundException(Integer id) {
    super("Could not find Breed with id " + id);
}public BreedNotFoundException(String breed) {
    super("Could not find Breed: " + breed);
}public BreedNotFoundException(Breed breed) {
    super("Could not find Breed: " + breed.toString());
}
}