package ar.com.veterinaria.app.entities.exception.validationLength;
 import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ar.com.veterinaria.app.entities.user.User;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserPasswordValidationLengthDataException extends RuntimeException{

 private  long serialVersionUID;

public UserPasswordValidationLengthDataException(User user) {
    super("Length password is less than 8 character: " + user.getPassword().length() + " characters for the password set");
}
}