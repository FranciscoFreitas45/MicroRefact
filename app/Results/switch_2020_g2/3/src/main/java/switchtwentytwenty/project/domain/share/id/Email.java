package switchtwentytwenty.project.domain.share.id;
 import switchtwentytwenty.project.exception.InvalidEmailException;
import java.util.Objects;
import java.util.regex.Pattern;
public class Email implements ID{

 private  String emailAddress;

// Constructor Methods
/**
 * Sole Constructor
 *
 * @param emailAddress - email address
 */
public Email(String emailAddress) throws InvalidEmailException {
    isValid(emailAddress);
    this.emailAddress = emailAddress.trim();
}
public boolean checkFormat(String emailAddress){
    String emailRegex = "[A-Z0-9a-z._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";
    Pattern pat = Pattern.compile(emailRegex);
    return pat.matcher(emailAddress).matches();
}


@Override
public int hashCode(){
    return Objects.hash(emailAddress);
}


public void isValid(String emailAddress){
    if (emailAddress == null) {
        throw new NullPointerException("The email Address can't be null");
    }
    if (emailAddress.trim().length() == 0) {
        throw new InvalidEmailException("The email Address can't have blank spaces.");
    }
    if (!checkFormat(emailAddress)) {
        throw new InvalidEmailException("The email Address is not in the correct format.");
    }
}


@Override
public boolean equals(Object object){
    if (this == object)
        return true;
    if (object == null || getClass() != object.getClass())
        return false;
    Email email = (Email) object;
    return Objects.equals(emailAddress, email.emailAddress);
}


@Override
public String toString(){
    return this.emailAddress;
}


}