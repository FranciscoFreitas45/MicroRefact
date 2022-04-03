package pl.szymanski.sharelibrary.exceptions.users;
 import pl.szymanski.sharelibrary.exceptions.ExceptionMessages;
public class InvalidEmailAddress extends RuntimeException{

public InvalidEmailAddress(String message) {
    super(String.format(ExceptionMessages.INVALID_EMAIL_ADDRESS_EXCEPTION_FORMAT, message));
}
}