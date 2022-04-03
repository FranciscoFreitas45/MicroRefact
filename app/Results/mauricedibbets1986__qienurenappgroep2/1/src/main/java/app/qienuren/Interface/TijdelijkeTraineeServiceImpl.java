package app.qienuren.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.Interface.TijdelijkeTraineeService;
public class TijdelijkeTraineeServiceImpl implements TijdelijkeTraineeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Iterable<TijdelijkeTrainee> getAllTijdelijkeTrainee(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllTijdelijkeTrainee"))
;  Iterable<TijdelijkeTrainee> aux = restTemplate.getForObject(builder.toUriString(), Iterable<TijdelijkeTrainee>.class);

 return aux;
}


}