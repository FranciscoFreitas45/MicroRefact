package pl.szymanski.sharelibrary.exceptions.auth;
 import pl.szymanski.sharelibrary.exceptions.ExceptionMessages;
public class InvalidUsernameEmailOrPassword extends RuntimeException{

public InvalidUsernameEmailOrPassword() {
    super(ExceptionMessages.INVALID_USERNAME_EMAIL_OR_PASSWORD);
}
}