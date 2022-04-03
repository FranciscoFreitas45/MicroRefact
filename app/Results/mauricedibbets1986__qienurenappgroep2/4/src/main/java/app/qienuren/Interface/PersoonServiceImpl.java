package app.qienuren.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.Interface.PersoonService;
public class PersoonServiceImpl implements PersoonService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Persoon getById(long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getById"))
    .queryParam("id",id)
;  Persoon aux = restTemplate.getForObject(builder.toUriString(), Persoon.class);

 return aux;
}


}