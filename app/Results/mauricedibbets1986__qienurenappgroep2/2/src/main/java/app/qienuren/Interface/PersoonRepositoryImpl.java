package app.qienuren.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.Interface.PersoonRepository;
public class PersoonRepositoryImpl implements PersoonRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Persoon findByEmail(String email){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmail"))
    .queryParam("email",email)
;  Persoon aux = restTemplate.getForObject(builder.toUriString(), Persoon.class);

 return aux;
}


}