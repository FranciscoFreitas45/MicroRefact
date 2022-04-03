package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.UserDetailsServiceImpl;
public class UserDetailsServiceImplImpl implements UserDetailsServiceImpl{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public UserDetails loadUserById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loadUserById"))
    .queryParam("id",id)
;  UserDetails aux = restTemplate.getForObject(builder.toUriString(), UserDetails.class);

 return aux;
}


}