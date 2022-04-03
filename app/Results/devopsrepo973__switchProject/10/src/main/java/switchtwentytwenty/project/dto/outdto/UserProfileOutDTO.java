package switchtwentytwenty.project.dto.outdto;
 import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import java.util.List;
import java.util.Objects;
@NoArgsConstructor
public class UserProfileOutDTO extends RepresentationModel<UserProfileOutDTO>{

@Setter(AccessLevel.PRIVATE)
@Getter
 private  String name;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  String vat;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  String birthDate;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  String mainEmail;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  List<String> otherEmails;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  List<String> telephoneNumbers;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  String houseNumber;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  String street;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  String city;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  String country;

@Setter(AccessLevel.PRIVATE)
@Getter
 private  String zipCode;

 private  UserProfileOutDTO dto;


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