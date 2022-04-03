package switchtwentytwenty.project.dto.outdto.UserProfileOutDTO;
 import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import java.util.List;
import java.util.Objects;
public class UserProfileDTOBuilder {

 private  UserProfileOutDTO dto;

public UserProfileDTOBuilder() {
    this.dto = new UserProfileOutDTO();
}
public UserProfileDTOBuilder withTelephoneNumbers(List<String> telephoneNumbers){
    dto.setTelephoneNumbers(telephoneNumbers);
    return this;
}


public UserProfileDTOBuilder withOtherEmails(List<String> otherEmails){
    dto.setOtherEmails(otherEmails);
    return this;
}


public UserProfileDTOBuilder withCity(String city){
    dto.setCity(city);
    return this;
}


public UserProfileOutDTO build(){
    Objects.requireNonNull(dto.name);
    Objects.requireNonNull(dto.birthDate);
    Objects.requireNonNull(dto.mainEmail);
    Objects.requireNonNull(dto.vat);
    Objects.requireNonNull(dto.city);
    Objects.requireNonNull(dto.country);
    Objects.requireNonNull(dto.zipCode);
    Objects.requireNonNull(dto.houseNumber);
    Objects.requireNonNull(dto.street);
    return this.dto;
}


public UserProfileDTOBuilder withVat(String vat){
    dto.setVat(vat);
    return this;
}


public UserProfileDTOBuilder withStreet(String street){
    dto.setStreet(street);
    return this;
}


public UserProfileDTOBuilder withHouseNumber(String houseNumber){
    dto.setHouseNumber(houseNumber);
    return this;
}


public UserProfileDTOBuilder withMainEmail(String mainEmail){
    dto.setMainEmail(mainEmail);
    return this;
}


public UserProfileDTOBuilder withBirthDate(String birthDate){
    dto.setBirthDate(birthDate);
    return this;
}


public UserProfileDTOBuilder withCountry(String country){
    dto.setCountry(country);
    return this;
}


public UserProfileDTOBuilder withName(String name){
    dto.setName(name);
    return this;
}


public UserProfileDTOBuilder withZipCode(String zipCode){
    dto.setZipCode(zipCode);
    return this;
}


}