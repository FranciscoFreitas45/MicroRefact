package DTO;
 import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
public class FormObjectMember {

 private  String username;

 private  String password;

 private  String confirmPassword;

 private  String name;

 private  String middleName;

 private  String surname;

 private  String photo;

 private  String email;

 private  String phoneNumber;

 private  String address;

 private  Boolean termsAndConditions;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


@Size(min = 5, max = 32)
@NotBlank
public String getConfirmPassword(){
    return this.confirmPassword;
}


@NotBlank
public String getName(){
    return this.name;
}


@URL
public String getPhoto(){
    return this.photo;
}


@Valid
public String getMiddleName(){
    return this.middleName;
}


@Size(min = 5, max = 32)
@Column(unique = true)
@NotBlank
public String getUsername(){
    return this.username;
}


@Size(min = 5, max = 32)
@NotBlank
public String getPassword(){
    return this.password;
}


@NotBlank
@Pattern(regexp = "[\\w.%-]+\\@[-.\\w]+\\.[A-Za-z]{2,4}|[\\w.%-]+\\<+[\\w.%-]+\\@[-.\\w]+\\.[A-Za-z]{2,4}|[\\w.%-]+\\<[\\w.%-]+\\@+\\>|[\\w.%-]+", message = "Email doesn't follow the pattern, must be identifier@domain.asd or alias <identifier@domain.asd>")
@Column(unique = true)
public String getEmail(){
    return this.email;
}


@Column(unique = true)
@Valid
public String getPhoneNumber(){
    return this.phoneNumber;
}


@Valid
public String getAddress(){
    return this.address;
}


@NotNull
public Boolean getTermsAndConditions(){
    return this.termsAndConditions;
}


@NotBlank
public String getSurname(){
    return this.surname;
}


public void setTermsAndConditions(Boolean termsAndConditions){
    this.termsAndConditions = termsAndConditions;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTermsAndConditions"))

.queryParam("termsAndConditions",termsAndConditions)
;
restTemplate.put(builder.toUriString(),null);
}


}