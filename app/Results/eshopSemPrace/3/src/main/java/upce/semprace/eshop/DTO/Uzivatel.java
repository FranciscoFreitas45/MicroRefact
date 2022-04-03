package upce.semprace.eshop.DTO;
import java.util.HashSet;
import java.util.Set;
  import upce.semprace.eshop.entity.*;

public class Uzivatel {

 private  Long id;

 private  String jmeno;

 private  String prijmeni;

 private  String email;

 private  String heslo;

 private  String adresa;

 private  String username;

 private  Set<Role> roles;

 private  Set<Nakup> nakup;

public Uzivatel(String jmeno, String prijmeni, String username, String email, String heslo) {
    this.jmeno = jmeno;
    this.prijmeni = prijmeni;
    this.email = email;
    this.heslo = heslo;
    this.username = username;
}public Uzivatel() {
}
public Set<Nakup> getNakup(){
    return nakup;
}


public String getHeslo(){
    return heslo;
}


public String getAdresa(){
    return adresa;
}


public Long getId(){
    return id;
}


public String getJmeno(){
    return jmeno;
}


public String getUsername(){
    return username;
}


public String getPrijmeni(){
    return prijmeni;
}


public String getEmail(){
    return email;
}


public Set<Role> getRoles(){
    return roles;
}


}