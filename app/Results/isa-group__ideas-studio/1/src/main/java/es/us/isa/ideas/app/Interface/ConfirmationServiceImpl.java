package es.us.isa.ideas.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.us.isa.ideas.app.Interface.ConfirmationService;
public class ConfirmationServiceImpl implements ConfirmationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void createPasswordResetConfirmation(String email){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createPasswordResetConfirmation"))
    .queryParam("email",email)
;
  restTemplate.put(builder.toUriString(), null);
}


}