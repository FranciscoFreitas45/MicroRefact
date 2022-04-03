package edu.xr.campusweibo.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import edu.xr.campusweibo.Interface.UserRepository;
public class UserRepositoryImpl implements UserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Optional<User> findOneByLogin(String login){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOneByLogin"))
    .queryParam("login",login)
;  Optional<User> aux = restTemplate.getForObject(builder.toUriString(), Optional<User>.class);

 return aux;
}


}