package ar.com.veterinaria.app.entities.exception.notFound;
 import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ar.com.veterinaria.app.entities.Address;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AddressNotFoundException extends RuntimeException{

 private  long serialVersionUID;

public AddressNotFoundException(Integer id) {
    super("Could not find Address with id " + id);
}public AddressNotFoundException(String address) {
    super("Could not find Address: " + address);
}public AddressNotFoundException(Address address) {
    super("Could not find Address: " + address.toString());
}
}