package app.qienuren.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.Interface.TijdelijkeInterneMedewerkerService;
public class TijdelijkeInterneMedewerkerServiceImpl implements TijdelijkeInterneMedewerkerService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Iterable<TijdelijkeInterneMedewerker> getallTijdelijkeMedewerkers(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getallTijdelijkeMedewerkers"))
;  Iterable<TijdelijkeInterneMedewerker> aux = restTemplate.getForObject(builder.toUriString(), Iterable<TijdelijkeInterneMedewerker>.class);

 return aux;
}


}