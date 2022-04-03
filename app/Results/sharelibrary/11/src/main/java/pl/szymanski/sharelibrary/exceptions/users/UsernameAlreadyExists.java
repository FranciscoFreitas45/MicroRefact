package pl.szymanski.sharelibrary.exceptions.users;
 import pl.szymanski.sharelibrary.exceptions.ExceptionMessages;
public class UsernameAlreadyExists extends RuntimeException{

public UsernameAlreadyExists(String username) {
    super(String.format(ExceptionMessages.USERNAME_ALREADY_EXISTS_EXCEPTION_FORMAT, username));
}
}