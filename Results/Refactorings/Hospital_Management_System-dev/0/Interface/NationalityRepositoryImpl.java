import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class NationalityRepositoryImpl implements NationalityRepository{

 private RestTemplate restTemplate;

  String url = "http://3";


public Nationality findByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByName"))
    .queryParam("name",name);
  Nationality aux = restTemplate.getForObject(builder.toUriString(), Nationality.class)

 return aux;
}


}