package ar.com.veterinaria.app.entities.exception.invalidData;
 import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ar.com.veterinaria.app.entities.Address;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AddressInvalidDataException extends RuntimeException{

 private  long serialVersionUID;

public AddressInvalidDataException(Address breed) {
    super("Invalid Breed Name: " + breed.getName() + ", it must be capital or lower letters");
}
}