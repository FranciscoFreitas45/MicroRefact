package pl.szymanski.sharelibrary.exceptions.users;
 import pl.szymanski.sharelibrary.exceptions.ExceptionMessages;
public class EmailAlreadyExist extends RuntimeException{

public EmailAlreadyExist(String email) {
    super(String.format(ExceptionMessages.EMAIL_ALREADY_EXISTS_EXCEPTION_FORMAT, email));
}
}