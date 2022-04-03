package app.qienuren.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.Interface.InterneMedewerkerService;
public class InterneMedewerkerServiceImpl implements InterneMedewerkerService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public InterneMedewerker addInterneMederwerker(InterneMedewerker interneMedewerker){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addInterneMederwerker"))
    .queryParam("interneMedewerker",interneMedewerker)
;  InterneMedewerker aux = restTemplate.getForObject(builder.toUriString(), InterneMedewerker.class);

 return aux;
}


public Iterable<InterneMedewerker> getAllInterneMedewerkers(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllInterneMedewerkers"))
;  Iterable<InterneMedewerker> aux = restTemplate.getForObject(builder.toUriString(), Iterable<InterneMedewerker>.class);

 return aux;
}


public InterneMedewerker wijzigGegevens(long oorspronkelijkeId,long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/wijzigGegevens"))
    .queryParam("oorspronkelijkeId",oorspronkelijkeId)
    .queryParam("id",id)
;  InterneMedewerker aux = restTemplate.getForObject(builder.toUriString(), InterneMedewerker.class);

 return aux;
}


}