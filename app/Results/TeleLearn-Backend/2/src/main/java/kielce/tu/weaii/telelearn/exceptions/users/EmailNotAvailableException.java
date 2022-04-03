package kielce.tu.weaii.telelearn.exceptions.users;
 import kielce.tu.weaii.telelearn.exceptions.BusinessLogicException;
public class EmailNotAvailableException extends BusinessLogicException{

public EmailNotAvailableException(String email) {
    super(String.format("Adress email %s jest już w użyciu", email));
}
}