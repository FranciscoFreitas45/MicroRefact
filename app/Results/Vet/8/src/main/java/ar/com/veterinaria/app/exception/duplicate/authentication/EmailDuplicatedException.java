package ar.com.veterinaria.app.exception.duplicate.authentication;
 import ar.com.veterinaria.app.entities.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailDuplicatedException extends RuntimeException{

 private  long serialVersionUID;

public EmailDuplicatedException(User userEmail) {
    super("The User Email" + userEmail.getEmail() + "' already exists.");
}
}