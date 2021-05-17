import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SlabMasterRepositoryImpl implements SlabMasterRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findAll"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


}