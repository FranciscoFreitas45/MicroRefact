package pl.edu.wat.wcy.pz.restaurantServer.form;
 import lombok.Getter;
import lombok.Setter;
import java.util.Set;
@Getter
@Setter
public class SignUpForm {

 private  String mail;

 private  String firstName;

 private  String lastName;

 private  Set<String> roles;

 private  String password;


public void setLastName(String lastName){
    this.lastName = lastName;
}


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setMail(String mail){
    this.mail = mail;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public String getFirstName(){
    return firstName;
}


public String getLastName(){
    return lastName;
}


public void setRoles(Set<String> roles){
    this.roles = roles;
}


public String getMail(){
    return mail;
}


public Set<String> getRoles(){
    return roles;
}


}