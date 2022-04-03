package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class UserSessionImpl implements UserSession{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public IUserRolePermissions getCurrentPermissions(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCurrentPermissions"))
  IUserRolePermissions aux = restTemplate.getForObject(builder.toUriString(), IUserRolePermissions.class);

 return aux;
}


public Evaluatee toEvaluatee(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toEvaluatee"))
  Evaluatee aux = restTemplate.getForObject(builder.toUriString(), Evaluatee.class);

 return aux;
}


public void assertLoggedIn(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/assertLoggedIn"))

  restTemplate.put(builder.toUriString(), null);
}


public IUserRolePermissions getUserRolePermissions(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserRolePermissions"))
  IUserRolePermissions aux = restTemplate.getForObject(builder.toUriString(), IUserRolePermissions.class);

 return aux;
}


public int getHttpCacheMaxAge(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getHttpCacheMaxAge"))
  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public String getAD_Language(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAD_Language"))
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public UserRolePermissionsKey getUserRolePermissionsKey(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserRolePermissionsKey"))
  UserRolePermissionsKey aux = restTemplate.getForObject(builder.toUriString(), UserRolePermissionsKey.class);

 return aux;
}


public Object hasPermission(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hasPermission"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}