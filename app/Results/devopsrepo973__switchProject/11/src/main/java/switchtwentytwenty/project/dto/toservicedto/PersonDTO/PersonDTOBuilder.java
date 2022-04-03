package switchtwentytwenty.project.dto.toservicedto.PersonDTO;
 import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
public class PersonDTOBuilder {

 private  PersonDTO dto;

public PersonDTOBuilder() {
    this.dto = new PersonDTO();
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