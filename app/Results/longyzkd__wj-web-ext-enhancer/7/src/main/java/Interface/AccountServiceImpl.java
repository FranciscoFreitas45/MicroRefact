package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.AccountService;
public class AccountServiceImpl implements AccountService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Object unique(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/unique"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public User findUserByLoginName(String loginName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findUserByLoginName"))
    .queryParam("loginName",loginName);
  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


public Object list(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/list"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}