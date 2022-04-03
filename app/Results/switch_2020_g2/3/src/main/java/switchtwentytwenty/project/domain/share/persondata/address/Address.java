package switchtwentytwenty.project.domain.share.persondata.address;
 import lombok.Getter;
import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.util.Util;
import java.util.Objects;
import java.util.regex.Pattern;
public class Address implements ValueObject{

@Getter
 private  HouseNumber houseNumber;

@Getter
 private  Street street;

@Getter
 private  City city;

@Getter
 private  ZipCode zipCode;

@Getter
 private  Country country;

// Constructor Methods
/**
 * Sole constructor.
 *
 * @param street      in which the person lives
 * @param houseNumber - number of the house or apartment in which the person lives
 * @param zipCode     - postal code in the format dddd-ddd
 * @param city        in which the person lives
 * @param country     in which the person lives
 */
public Address(String street, String houseNumber, String zipCode, String city, String country) {
    isValid(houseNumber, street, city, country, zipCode);
    houseNumber = Util.capitalizeFirstLetters(houseNumber);
    street = Util.capitalizeFirstLetters(street);
    city = Util.capitalizeFirstLetters(city);
    country = Util.capitalizeFirstLetters(country);
    this.houseNumber = new HouseNumber(houseNumber);
    this.street = new Street(street);
    this.city = new City(city);
    this.country = new Country(country);
    this.zipCode = new ZipCode(zipCode);
}
@Override
public int hashCode(){
    return Objects.hash(houseNumber, street, city, zipCode, country);
}


public void isValid(String houseNumber,String street,String city,String country,String zipCode){
    if (houseNumber == null || street == null || city == null || country == null || zipCode == null) {
        throw new NullPointerException("None of the address information can be null");
    }
    if (houseNumber.trim().length() == 0 || street.trim().length() == 0 || city.trim().length() == 0 || country.trim().length() == 0 || zipCode.trim().length() == 0) {
        throw new IllegalArgumentException("None of the address information can have blank spaces");
    }
    if (!checkZipCodeFormat(zipCode)) {
        throw new IllegalArgumentException("Zip code is not in the correct format");
    }
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    Address address = (Address) o;
    return Objects.equals(houseNumber.denomination, address.houseNumber.denomination) && Objects.equals(street.denomination, address.street.denomination) && Objects.equals(city.denomination, address.city.denomination) && Objects.equals(country.denomination, address.country.denomination) && Objects.equals(zipCode.denomination, address.zipCode.denomination);
}


@Override
public String toString(){
    return "Street:" + street.denomination + ", House Number:" + houseNumber.denomination + ", ZipCode:" + zipCode.denomination + ", City:" + city.denomination + ", Country:" + country.denomination;
}


public boolean checkZipCodeFormat(String zipCode){
    String zipCodeRegex = "\\d{4}(-\\d{3})?";
    Pattern pat = Pattern.compile(zipCodeRegex);
    return pat.matcher(zipCode).matches();
}


}