package es.us.isa.ideas.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.us.isa.ideas.app.Interface.UserAccountService;
public class UserAccountServiceImpl implements UserAccountService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Collection<UserAccount> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  Collection<UserAccount> aux = restTemplate.getForObject(builder.toUriString(), Collection<UserAccount>.class);

 return aux;
}


public UserAccount create(Actor actor){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("actor",actor)
;  UserAccount aux = restTemplate.getForObject(builder.toUriString(), UserAccount.class);

 return aux;
}


}