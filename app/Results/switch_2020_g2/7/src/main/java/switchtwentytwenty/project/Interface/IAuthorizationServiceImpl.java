package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.IAuthorizationService;
public class IAuthorizationServiceImpl implements IAuthorizationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public void registerUser(SignupDTO signUpRequest){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/registerUser"))
    .queryParam("signUpRequest",signUpRequest)
;
  restTemplate.put(builder.toUriString(), null);
}


}