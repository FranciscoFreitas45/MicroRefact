package switchtwentytwenty.project.domain.share.familydata;
 import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import java.util.Objects;
public class FamilyName implements ValueObject{

 private  String name;

// Constructor Methods
/**
 * Sole Constructor
 * @param name
 */
public FamilyName(String name) {
    if (!isValidName(name)) {
        throw new IllegalArgumentException("Invalid family name");
    }
    this.name = name;
}
public boolean isValidName(String name){
    if (name == null || name.trim().length() == 0) {
        return false;
    }
    return name.matches("[A-zÀ-ÿ\\s']*");
// Does not allow for special characters or numeric characters but does allow for apostrophes and for
// accents (for example á)
}


@Override
public int hashCode(){
    return Objects.hash(name);
}


@Override
public boolean equals(Object other){
    if (this == other)
        return true;
    if (other == null || getClass() != other.getClass())
        return false;
    FamilyName that = (FamilyName) other;
    return Objects.equals(name, that.name);
}


@Override
public String toString(){
    return this.name;
}


}