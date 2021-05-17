import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class NationalityServiceImpl implements NationalityService{

 private RestTemplate restTemplate;

  String url = "http://3";


public List<Nationality> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
  List<Nationality> aux = restTemplate.getForObject(builder.toUriString(), List<Nationality>.class)

 return aux;
}


}