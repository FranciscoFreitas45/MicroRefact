package upce.semprace.eshop.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence;
import java.util.HashSet;
import java.util.Set;
import upce.semprace.eshop.Request.NakupRequest;
import upce.semprace.eshop.Request.Impl.NakupRequestImpl;
import upce.semprace.eshop.DTO.Nakup;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";

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


public void setJmeno(String jmeno){
    this.jmeno = jmeno;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setJmeno"))

.queryParam("jmeno",jmeno)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPrijmeni(String prijmeni){
    this.prijmeni = prijmeni;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPrijmeni"))

.queryParam("prijmeni",prijmeni)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAdresa(String adresa){
    this.adresa = adresa;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAdresa"))

.queryParam("adresa",adresa)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRoles(Set<Role> roles){
    this.roles = roles;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRoles"))

.queryParam("roles",roles)
;
restTemplate.put(builder.toUriString(),null);
}


}