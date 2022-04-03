package es.us.isa.ideas.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.us.isa.ideas.app.Interface.UserAccountRepository;
public class UserAccountRepositoryImpl implements UserAccountRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public UserAccount findByUsername(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUsername"))
    .queryParam("username",username)
;  UserAccount aux = restTemplate.getForObject(builder.toUriString(), UserAccount.class);

 return aux;
}


}