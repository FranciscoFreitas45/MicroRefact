package app.qienuren.DTO;
 import app.qienuren.controller.PersoonService;
import app.qienuren.security.RandomPasswordGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence;
public class Persoon {

 private  long id;

 private  String naam;

 private  String email;

 private  String telefoonnr;

 private  String userName;

 private  String password;

 private  String straatNaamNr;

 private  String postcode;

 private  String woonplaats;

 private  String roles;

 private  boolean active;


public String getPostcode(){
    return postcode;
}


public long getId(){
    return id;
}


public String getPassword(){
    return password;
}


public String getTelefoonnr(){
    return telefoonnr;
}


public String getWoonplaats(){
    return woonplaats;
}


public String getNaam(){
    return naam;
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


public String getRoles(){
    return roles;
}


}