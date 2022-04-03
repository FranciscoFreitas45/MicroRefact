package com.evolvingreality.onleave.web.rest.dto;
 import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
public class UserDTO {

 public  int PASSWORD_MIN_LENGTH;

 public  int PASSWORD_MAX_LENGTH;

@NotNull
@Size(max = 50)
 private  String firstName;

@NotNull
@Size(max = 50)
 private  String lastName;

 private  String password;

@NotNull
@Email
@Size(min = 5, max = 100)
 private  String email;

@Size(min = 2, max = 5)
 private  String langKey;

 private  Long securityGroupId;

 private  Long managerId;

 private  List<String> roles;

public UserDTO() {
}public UserDTO(String firstName, String lastName, String email, String langKey, List<String> roles) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.langKey = langKey;
    this.roles = roles;
}
public void setPassword(String password){
    this.password = password;
}


public Long getManagerId(){
    return managerId;
}


public void setManagerId(Long managerId){
    this.managerId = managerId;
}


public Long getSecurityGroupId(){
    return securityGroupId;
}


public String getLangKey(){
    return langKey;
}


public void setLangKey(String langKey){
    this.langKey = langKey;
}


public String getLastName(){
    return lastName;
}


public void setLastName(String lastName){
    this.lastName = lastName;
}


public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return "UserDTO{" + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", langKey='" + langKey + '\'' + ", roles=" + roles + '}';
}


public void setSecurityGroupId(Long securityGroupId){
    this.securityGroupId = securityGroupId;
}


public void setRoles(List<String> roles){
    this.roles = roles;
}


public String getFirstName(){
    return firstName;
}


public List<String> getRoles(){
    return roles;
}


}