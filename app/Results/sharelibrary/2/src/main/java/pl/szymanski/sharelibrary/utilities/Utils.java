package pl.szymanski.sharelibrary.utilities;
 import pl.szymanski.sharelibrary.exceptions.users.InvalidEmailAddress;
public class Utils {


public void validateEmailAddress(String email){
    if (!email.matches(Constants.EMAIL_REGEX_EXPRESION))
        throw new InvalidEmailAddress(email);
}


}