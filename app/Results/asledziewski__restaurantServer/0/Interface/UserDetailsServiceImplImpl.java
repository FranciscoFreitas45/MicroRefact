import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class UserDetailsServiceImplImpl implements UserDetailsServiceImpl{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public UserDetails loadUserByUsername(String s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loadUserByUsername"))
    .queryParam("s",s);
  UserDetails aux = restTemplate.getForObject(builder.toUriString(), UserDetails.class)

 return aux;
}


}