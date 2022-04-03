package app.qienuren.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.DTO.KlantContactPersoon;
import app.qienuren.Request.KlantContactPersoonRequest;
public class KlantContactPersoonRequestImpl implements KlantContactPersoonRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setLeidingGevende(KlantContactPersoon leidingGevende,long id0KCA){
 restTemplate.put("http://1/Trainee/{id}/KlantContactPersoon/setLeidingGevende",leidingGevende,id0KCA);
 return ;
}


public KlantContactPersoon getLeidingGevende(long id0KCA){
 KlantContactPersoon aux = restTemplate.getForObject("http://1/Trainee/{id}/KlantContactPersoon/getLeidingGevende",KlantContactPersoon.class,id0KCA);
return aux;
}


public void koppelKlantContactPersoon(KlantContactPersoon kcp,long id0KCA){
 restTemplate.put("http://1/Trainee/{id}/KlantContactPersoon/koppelKlantContactPersoon",kcp,id0KCA);
 return ;
}


}