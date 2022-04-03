package es.us.isa.ideas.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.us.isa.ideas.app.Interface.UserAccountController;
public class UserAccountControllerImpl implements UserAccountController{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public boolean save(UserAccount userAccount,String oldPass,String newPass){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("userAccount",userAccount)
    .queryParam("oldPass",oldPass)
    .queryParam("newPass",newPass)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}