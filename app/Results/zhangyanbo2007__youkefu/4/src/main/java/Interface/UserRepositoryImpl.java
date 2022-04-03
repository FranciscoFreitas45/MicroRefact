package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.UserRepository;
public class UserRepositoryImpl implements UserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<User> findAll(Specification<User> spec){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("spec",spec)
;  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


}