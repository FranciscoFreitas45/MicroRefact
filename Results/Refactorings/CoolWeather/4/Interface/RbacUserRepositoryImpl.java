import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class RbacUserRepositoryImpl implements RbacUserRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public int deleteUserNonExistDefault(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("deleteUserNonExistDefault"))
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public Object saveAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("saveAll"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


}