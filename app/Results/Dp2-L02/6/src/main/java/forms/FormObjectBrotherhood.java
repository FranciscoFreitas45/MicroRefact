package forms;
 import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
public class FormObjectBrotherhood {

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

 private  String title;


public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
}


@Size(min = 5, max = 32)
@NotBlank
public String getConfirmPassword(){
    return this.confirmPassword;
}


public void setConfirmPassword(String confirmPassword){
    this.confirmPassword = confirmPassword;
}


@NotBlank
public String getName(){
    return this.name;
}


public void setUsername(String username){
    this.username = username;
}


@URL
public String getPhoto(){
    return this.photo;
}


public void setAddress(String address){
    this.address = address;
}


public void setTermsAndConditions(Boolean termsAndConditions){
    this.termsAndConditions = termsAndConditions;
}


public void setTitle(String title){
    this.title = title;
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
public String getTitle(){
    return this.title;
}


public void setEmail(String email){
    this.email = email;
}


public void setSurname(String surname){
    this.surname = surname;
}


public void setPhoneNumber(String phoneNumber){
    this.phoneNumber = phoneNumber;
}


public void setPhoto(String photo){
    this.photo = photo;
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


public void setMiddleName(String middleName){
    this.middleName = middleName;
}


@NotBlank
public String getSurname(){
    return this.surname;
}


}