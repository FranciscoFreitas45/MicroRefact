package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SponsorshipService;
public class SponsorshipServiceImpl implements SponsorshipService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<Sponsorship> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<Sponsorship> aux = restTemplate.getForObject(builder.toUriString(), List<Sponsorship>.class);

 return aux;
}


public Object isEmpty(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}