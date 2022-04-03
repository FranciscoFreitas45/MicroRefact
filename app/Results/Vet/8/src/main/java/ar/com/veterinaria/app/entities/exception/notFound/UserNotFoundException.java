package ar.com.veterinaria.app.entities.exception.notFound;
 import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ar.com.veterinaria.app.entities.user.User;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends RuntimeException{

 private  long serialVersionUID;

public UserNotFoundException(Integer id) {
    super("Could not find User with id " + id);
}public UserNotFoundException(String user) {
    super("Could not find User: " + user);
}public UserNotFoundException(User user) {
    super("Could not find User: " + user.toString());
}
}