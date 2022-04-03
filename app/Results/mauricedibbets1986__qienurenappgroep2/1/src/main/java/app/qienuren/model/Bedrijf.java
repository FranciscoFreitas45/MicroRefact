package app.qienuren.model;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Bedrijf {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

@OneToMany(cascade = CascadeType.ALL)
@JsonBackReference(value = "bedrijf")
 private  List<KlantContactPersoon> contactPersonen;

 private  String naam;

 private  String email;

 private  String telefoonnr;

 private  String straatNaamNr;

 private  String postcode;

 private  String woonplaats;


public void setContactPersonen(List<KlantContactPersoon> contactPersonen){
    this.contactPersonen = contactPersonen;
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


public List<KlantContactPersoon> getContactPersonen(){
    return contactPersonen;
}


public void koppelContactPersoon(KlantContactPersoon klantContactPersoon){
    this.contactPersonen.add(klantContactPersoon);
}


public void setEmail(String emailadres){
    this.email = emailadres;
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


public String getStraatNaamNr(){
    return straatNaamNr;
}


}