package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class UserSessionImpl implements UserSession{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public void assertLoggedIn(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/assertLoggedIn"))

  restTemplate.put(builder.toUriString(), null);
}


public IUserRolePermissions getUserRolePermissions(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserRolePermissions"))
  IUserRolePermissions aux = restTemplate.getForObject(builder.toUriString(), IUserRolePermissions.class);

 return aux;
}


public Object checkWindowPermission(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/checkWindowPermission"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}