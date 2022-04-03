package ar.com.veterinaria.app.entities.exception.invalidData;
 import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ar.com.veterinaria.app.entities.user.User;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserPasswordInvalidDataException extends RuntimeException{

 private  long serialVersionUID;

public UserPasswordInvalidDataException(User user) {
    super("Invalid Password: " + user.getPassword() + "\n it must Capital and Lower letter, Number, Special Character");
}
}