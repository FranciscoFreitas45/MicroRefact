package switchtwentytwenty.project.dto.toservicedto;
 import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter(AccessLevel.PRIVATE)
public class PersonDTO {

 private  String name;

 private  String birthDate;

 private  String vat;

 private  String houseNumber;

 private  String street;

 private  String city;

 private  String country;

 private  String zipCode;

 private  List<String> phoneNumbers;

 private  String email;

 private  String familyID;

 private  String username;

 private  String password;

 private  PersonDTO dto;

public PersonDTO() {
    this.phoneNumbers = new ArrayList<>();
}
public PersonDTOBuilder withEmail(String email){
    dto.setEmail(email);
    return this;
}


public PersonDTOBuilder withUsername(String username){
    dto.setUsername(username);
    return this;
}


public PersonDTOBuilder withVat(String vat){
    dto.setVat(vat);
    return this;
}


public PersonDTOBuilder withPhoneNumbers(List<String> phoneNumbers){
    dto.setPhoneNumbers(phoneNumbers);
    return this;
}


public PersonDTOBuilder withHouseNumber(String houseNumber){
    dto.setHouseNumber(houseNumber);
    return this;
}


public PersonDTOBuilder withBirthDate(String birthDate){
    dto.setBirthDate(birthDate);
    return this;
}


public PersonDTOBuilder withCountry(String country){
    dto.setCountry(country);
    return this;
}


public PersonDTOBuilder withCity(String city){
    dto.setCity(city);
    return this;
}


public PersonDTO build(){
    return this.dto;
}


public PersonDTOBuilder withStreet(String street){
    dto.setStreet(street);
    return this;
}


public PersonDTOBuilder withPassword(String password){
    dto.setPassword(password);
    return this;
}


public PersonDTOBuilder withFamilyID(String familyID){
    dto.setFamilyID(familyID);
    return this;
}


public PersonDTOBuilder withName(String name){
    dto.setName(name);
    return this;
}


public PersonDTOBuilder withZipCode(String zipCode){
    dto.setZipCode(zipCode);
    return this;
}


}