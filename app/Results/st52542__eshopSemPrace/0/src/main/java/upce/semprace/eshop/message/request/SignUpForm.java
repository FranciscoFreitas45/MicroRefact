package upce.semprace.eshop.message.request;
 import java.util.Set;
import javax.validation.constraints;
public class SignUpForm {

@NotBlank
@Size(min = 3, max = 50)
 private  String firstname;

@NotBlank
@Size(min = 3, max = 50)
 private  String lastname;

@NotBlank
@Size(min = 3, max = 50)
 private  String username;

@NotBlank
@Size(max = 60)
@Email
 private  String email;

@NotBlank
@Size(max = 60)
@Email
 private  String address;

 private  Set<String> role;

@NotBlank
@Size(min = 6, max = 40)
 private  String password;


public void setPassword(String password){
    this.password = password;
}


public void setLastname(String lastname){
    this.lastname = lastname;
}


public String getFirstname(){
    return firstname;
}


public void setFirstname(String firstname){
    this.firstname = firstname;
}


public void setUsername(String username){
    this.username = username;
}


public void setAddress(String address){
    this.address = address;
}


public Set<String> getRole(){
    return this.role;
}


public String getUsername(){
    return username;
}


public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public void setRole(Set<String> role){
    this.role = role;
}


public String getEmail(){
    return email;
}


public String getAddress(){
    return address;
}


public String getLastname(){
    return lastname;
}


}