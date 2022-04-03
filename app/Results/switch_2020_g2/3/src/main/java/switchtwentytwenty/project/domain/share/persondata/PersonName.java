package switchtwentytwenty.project.domain.share.persondata;
 import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.exception.InvalidPersonNameException;
import switchtwentytwenty.project.util.Util;
import java.util.Objects;
public class PersonName implements ValueObject{

 private  String name;

// Constructor Methods
/**
 * Sole Constructor
 * @param name - person name
 * @throws InvalidPersonNameException
 */
public PersonName(String name) throws InvalidPersonNameException {
    isValidName(name);
    this.name = Util.capitalizeFirstLetters(name);
}
public void isValidName(String name){
    if (name == null || name.trim().length() == 0 || !name.matches("[A-zÀ-ÿ\\s']*")) {
        throw new InvalidPersonNameException("Person name invalid");
    }
}


@Override
public int hashCode(){
    return Objects.hash(name);
}


@Override
public boolean equals(Object object){
    if (this == object)
        return true;
    if (object == null || getClass() != object.getClass())
        return false;
    PersonName that = (PersonName) object;
    return Objects.equals(name, that.name);
}


@Override
public String toString(){
    return name;
}


}