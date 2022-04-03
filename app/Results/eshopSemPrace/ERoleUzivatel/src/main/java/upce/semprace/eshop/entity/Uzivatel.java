package upce.semprace.eshop.entity;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import upce.semprace.eshop.Request.NakupRequest;
import upce.semprace.eshop.Request.Impl.NakupRequestImpl;
import upce.semprace.eshop.DTO.Nakup;
@Entity
public class Uzivatel {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(length = 45, nullable = false)
 private  String jmeno;

@Column(length = 45, nullable = false)
 private  String prijmeni;

@Column(length = 100, nullable = false, unique = true)
 private  String email;

@Column(length = 100, nullable = false)
 private  String heslo;

@Column(length = 100, nullable = false)
 private  String adresa;

@Column(length = 100)
 private  String username;

@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
 private  Set<Role> roles;

@Transient
 private  Set<Nakup> nakup;

@Transient
 private NakupRequest nakuprequest = new NakupRequestImpl();;

public Uzivatel(String jmeno, String prijmeni, String username, String email, String heslo) {
    this.jmeno = jmeno;
    this.prijmeni = prijmeni;
    this.email = email;
    this.heslo = heslo;
    this.username = username;
}public Uzivatel() {
}
public void setHeslo(String heslo){
    this.heslo = heslo;
}


public void setUsername(String username){
    this.username = username;
}


public Set<Nakup> getNakup(){
  this.nakup = nakuprequest.getNakup(this.id);
return this.nakup;
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


public void setPrijmeni(String prijmeni){
    this.prijmeni = prijmeni;
}


public String getJmeno(){
    return jmeno;
}


public void setAdresa(String adresa){
    this.adresa = adresa;
}


public String getUsername(){
    return username;
}


public void setEmail(String email){
    this.email = email;
}


public void setJmeno(String jmeno){
    this.jmeno = jmeno;
}


public String getPrijmeni(){
    return prijmeni;
}


public void setNakup(Set<Nakup> nakup){
 nakuprequest.setNakup(nakup,this.id);
}



public void setId(Long id){
    this.id = id;
}


public String getEmail(){
    return email;
}


public void setRoles(Set<Role> roles){
    this.roles = roles;
}


public Set<Role> getRoles(){
    return roles;
}


}