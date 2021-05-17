import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class UserServiceImpl implements UserService{

 private RestTemplate restTemplate;

  String url = "http://0";


public User currentUser(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/currentUser"))
  User aux = restTemplate.getForObject(builder.toUriString(), User.class)

 return aux;
}


}