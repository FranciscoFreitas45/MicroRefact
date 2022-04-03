package switchtwentytwenty.project.domain.share.persondata;
 import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import java.util.Objects;
public class TelephoneNumber implements ValueObject{

 private  int TELEPHONE_NUMBER_LENGTH;

 private  String number;

// Constructor methods
/**
 * Sole Constructor
 *
 * @param phoneNumber - phoneNumber
 */
public TelephoneNumber(String phoneNumber) {
    isValid(phoneNumber);
    this.number = phoneNumber;
}
@Override
public int hashCode(){
    return Objects.hash(number);
}


public void isValid(String phoneNumber){
    if (phoneNumber == null) {
        throw new NullPointerException("Phone number can't be null");
    }
    if (phoneNumber.trim().length() == 0) {
        throw new IllegalArgumentException("Phone number can't have blank spaces");
    }
    if (phoneNumber.length() != TELEPHONE_NUMBER_LENGTH || !checkPhoneNumberFormat(phoneNumber)) {
        throw new IllegalArgumentException("Phone number is not in the correct format");
    }
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    TelephoneNumber that = (TelephoneNumber) o;
    return Objects.equals(number, that.number);
}


@Override
public String toString(){
    return this.number;
}


public boolean checkPhoneNumberFormat(String phoneNumber){
    return phoneNumber.matches("(9)?[1-3&6][0-9]{7}") || phoneNumber.matches("(2)?[1-9][0-9]{7}");
}


}