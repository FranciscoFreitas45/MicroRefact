package app.qienuren.DTO;
 import javax.persistence;
import java.util.List;
public class TijdelijkeTrainee extends TijdelijkePersoon{

 private  long id;

 private  long oorspronkelijkeId;

 private  String naam;

 private  String email;

 private  String telefoonnr;

 private  String userName;

 private  String password;

 private  String straatNaamNr;

 private  String postcode;

 private  String woonplaats;

 private  List<TijdelijkeTrainee> tijdelijkeTrainees;


public void setTijdelijkeTrainees(List<TijdelijkeTrainee> tijdelijkeTrainees){
    this.tijdelijkeTrainees = tijdelijkeTrainees;
}


public String getPostcode(){
    return postcode;
}


public void setStraatNaamNr(String straatNaamNr){
    this.straatNaamNr = straatNaamNr;
}


public long getId(){
    return id;
}


public long getOorspronkelijkeId(){
    return oorspronkelijkeId;
}


public String getPassword(){
    return password;
}


public List<TijdelijkeTrainee> getAllTijdelijkeTrainee(){
    return tijdelijkeTrainees;
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


public String getNaam(){
    return naam;
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


}