package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.IAuthorizationService;
public class IAuthorizationServiceImpl implements IAuthorizationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public String getRole(String role){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRole"))
    .queryParam("role",role)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}