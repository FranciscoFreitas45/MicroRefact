package app.qienuren.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.Interface.PersoonService;
public class PersoonServiceImpl implements PersoonService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Iterable<Persoon> getAllMedewerkers(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllMedewerkers"))
;  Iterable<Persoon> aux = restTemplate.getForObject(builder.toUriString(), Iterable<Persoon>.class);

 return aux;
}


public Persoon wijzigGegevens(long oorspronkelijkeId,long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/wijzigGegevens"))
    .queryParam("oorspronkelijkeId",oorspronkelijkeId)
    .queryParam("id",id)
;  Persoon aux = restTemplate.getForObject(builder.toUriString(), Persoon.class);

 return aux;
}


}