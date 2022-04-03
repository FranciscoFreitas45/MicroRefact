package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class UserSessionImpl implements UserSession{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public UserId getLoggedUserId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLoggedUserId"))
  UserId aux = restTemplate.getForObject(builder.toUriString(), UserId.class);

 return aux;
}


public void assertLoggedIn(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/assertLoggedIn"))

  restTemplate.put(builder.toUriString(), null);
}


public OrgId getOrgId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOrgId"))
  OrgId aux = restTemplate.getForObject(builder.toUriString(), OrgId.class);

 return aux;
}


public Object getRepoId(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRepoId"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public String getAD_Language(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAD_Language"))
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}