import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class UserServiceImpl implements UserService{

 private RestTemplate restTemplate;

  String url = "http://1";


public Optional<User> getUserById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserById"))
    .queryParam("id",id);
  Optional<User> aux = restTemplate.getForObject(builder.toUriString(), Optional<User>.class)

 return aux;
}


}