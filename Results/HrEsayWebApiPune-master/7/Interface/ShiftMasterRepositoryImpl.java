import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class ShiftMasterRepositoryImpl implements ShiftMasterRepository{

 private RestTemplate restTemplate;

  String url = "http://2";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


}