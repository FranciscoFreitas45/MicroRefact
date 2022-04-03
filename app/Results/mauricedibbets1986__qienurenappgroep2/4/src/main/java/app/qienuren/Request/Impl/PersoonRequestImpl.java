package app.qienuren.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.DTO.Persoon;
import app.qienuren.Request.PersoonRequest;
public class PersoonRequestImpl implements PersoonRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setMedewerker(Persoon medewerker,long idN8E9){
 restTemplate.put("http://3/Formulier/{id}/Persoon/setMedewerker",medewerker,idN8E9);
 return ;
}


public Persoon getMedewerker(long idN8E9){
 Persoon aux = restTemplate.getForObject("http://3/Formulier/{id}/Persoon/getMedewerker",Persoon.class,idN8E9);
return aux;
}


}