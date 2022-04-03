package forms;
 import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import domain.Area;
import Interface.Area;
public class FormObjectChapter {

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

 private  Area area;


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


@NotBlank
public String getName(){
    return this.name;
}


@URL
public String getPhoto(){
    return this.photo;
}


public void setTermsAndConditions(Boolean termsAndConditions){
    this.termsAndConditions = termsAndConditions;
}


public void setArea(Area area){
    this.area = area;
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


@NotBlank
public String getTitle(){
    return this.title;
}


public void setSurname(String surname){
    this.surname = surname;
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


public void setConfirmPassword(String confirmPassword){
    this.confirmPassword = confirmPassword;
}


public void setUsername(String username){
    this.username = username;
}


public void setAddress(String address){
    this.address = address;
}


public void setTitle(String title){
    this.title = title;
}


@Size(min = 5, max = 32)
@NotBlank
public String getPassword(){
    return this.password;
}


public void setEmail(String email){
    this.email = email;
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


@Valid
public Area getArea(){
    return this.area;
}


@NotBlank
public String getSurname(){
    return this.surname;
}


}