package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.AuthorizationService;
public class AuthorizationServiceImpl implements AuthorizationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public boolean accessFamilyCashAccountAuthorization(String role){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/accessFamilyCashAccountAuthorization"))
    .queryParam("role",role)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public Email getPersonIDOfUser(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPersonIDOfUser"))
    .queryParam("username",username)
;  Email aux = restTemplate.getForObject(builder.toUriString(), Email.class);

 return aux;
}


}