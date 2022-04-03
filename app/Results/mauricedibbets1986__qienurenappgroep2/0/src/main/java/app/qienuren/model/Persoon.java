package app.qienuren.model;
 import app.qienuren.controller.PersoonService;
import app.qienuren.security.RandomPasswordGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence;
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class Persoon {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

 private  String naam;

@Column(name = "email")
 private  String email;

 private  String telefoonnr;

 private  String userName;

 private  String password;

 private  String straatNaamNr;

 private  String postcode;

 private  String woonplaats;

 private  String roles;

 private  boolean active;


public void setPassword(String wachtwoord){
    this.password = wachtwoord;
}


public String getPostcode(){
    return postcode;
}


public void setTelefoonnr(String telefoonnr){
    this.telefoonnr = telefoonnr;
}


public void setStraatNaamNr(String straatNaamNr){
    this.straatNaamNr = straatNaamNr;
}


public long getId(){
    return id;
}


public void setNaam(String naam){
    this.naam = naam;
}


public boolean isActive(){
    return active;
}


public String getPassword(){
    return password;
}


public void setActive(boolean active){
    this.active = active;
}


public void setEmail(String email){
    this.email = email;
    this.userName = email;
}


public void setUserName(String userName){
    this.userName = userName;
}


public String getTelefoonnr(){
    return telefoonnr;
}


public String getWoonplaats(){
    return woonplaats;
}


public void setWoonplaats(String woonplaats){
    this.woonplaats = woonplaats;
}


public String getNaam(){
    return naam;
}


public void setPostcode(String postcode){
    this.postcode = postcode;
}


public void setId(long id){
    this.id = id;
}


public String getEmail(){
    return email;
}


public String getUserName(){
    return userName;
}


public String getStraatNaamNr(){
    return straatNaamNr;
}


public void setRoles(String roles){
    this.roles = roles;
}


public String getRoles(){
    return roles;
}


}