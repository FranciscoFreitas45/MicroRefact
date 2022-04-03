package switchtwentytwenty.project.domain.share.designation;
 import switchtwentytwenty.project.util.Util;
import java.util.Objects;
import java.util.regex.Pattern;
public class BaseDesignation {

 private  int MINIMAL_CHARACTERS;

 protected  String designation;

// Constructor
/**
 * Sole Constructor
 * @param designation - designation
 */
protected BaseDesignation(String designation) {
    this.designation = validateDesignation(designation);
}
public void postValidate(String designation){
    if (designation.length() < MINIMAL_CHARACTERS) {
        throw new IllegalArgumentException("Category must have at least 3 characters");
    }
}


public boolean checkFormat(String designation){
    // does not allow for special characters or numeric characters but does allow for apostrophes and
    String designationRegex = "[A-zÀ-ÿ\\s']*";
    // for accents (for example á)
    Pattern pat = Pattern.compile(designationRegex);
    return pat.matcher(designation).matches();
}


@Override
public int hashCode(){
    return Objects.hash(designation);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof BaseDesignation))
        return false;
    BaseDesignation that = (BaseDesignation) o;
    return Objects.equals(designation, that.designation);
}


public String validateDesignation(String designation){
    validate(designation);
    designation = Util.capitalizeFirstLetters(designation);
    postValidate(designation);
    return designation;
}


@Override
public String toString(){
    return this.designation;
}


public void validate(String designation){
    if (designation == null) {
        throw new NullPointerException("Category must not be null");
    }
    if (!checkFormat(designation)) {
        throw new IllegalArgumentException("Numeric characters and special characters are not allowed");
    }
}


}