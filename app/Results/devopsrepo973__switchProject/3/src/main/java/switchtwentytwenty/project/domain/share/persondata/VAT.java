package switchtwentytwenty.project.domain.share.persondata;
 import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.exception.InvalidVATException;
import java.util.Objects;
public class VAT implements ValueObject{

 private  String number;

// Constructor Methods
/**
 * Sole constructor.
 *
 * @param number - person's value added tax identification number
 */
public VAT(String number) throws InvalidVATException {
    isValidVAT(number);
    this.number = number;
}
public boolean checkFormat(String vat){
    if (!vat.matches("([1235689])[0-9]{8}")) {
        return false;
    }
    int checkSum = 0;
    final int maxDigits = 9;
    for (int index = 0; index < maxDigits - 1; index++) {
        checkSum += (vat.charAt(index)) * (maxDigits - index);
    }
    final int validationValue = 11;
    int checkDigit = validationValue - (checkSum % validationValue);
    final int checkDigitMaxValue = 10;
    if (checkDigit >= checkDigitMaxValue)
        checkDigit = 0;
    return checkDigit == vat.charAt(maxDigits - 1) - '0';
}


@Override
public int hashCode(){
    return Objects.hash(number);
}


public void isValidVAT(String vat){
    if (vat == null) {
        throw new InvalidVATException("Vat can't be null");
    }
    if (vat.trim().length() == 0) {
        throw new InvalidVATException("Vat can not have blank spaces");
    }
    if (!checkFormat(vat)) {
        throw new InvalidVATException("Vat is not in correct format");
    }
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    VAT vat = (VAT) o;
    return Objects.equals(number, vat.number);
}


@Override
public String toString(){
    return number;
}


}