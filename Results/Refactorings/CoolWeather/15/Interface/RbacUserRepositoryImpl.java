import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class RbacUserRepositoryImpl implements RbacUserRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<RbacUser> findAllNonExistDefault(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findAllNonExistDefault"))
  List<RbacUser> aux = restTemplate.getForObject(builder.toUriString(), List<RbacUser>.class)

 return aux;
}


}