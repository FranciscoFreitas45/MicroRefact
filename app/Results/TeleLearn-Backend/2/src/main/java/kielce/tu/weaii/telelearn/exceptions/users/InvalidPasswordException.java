package kielce.tu.weaii.telelearn.exceptions.users;
 import kielce.tu.weaii.telelearn.exceptions.BusinessLogicException;
public class InvalidPasswordException extends BusinessLogicException{

public InvalidPasswordException() {
    super("Obecne hasło jest nieprawidłowe");
}
}