package app.qienuren.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.Interface.EmailService;
public class EmailServiceImpl implements EmailService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public void sendWithAccountTemplate(Persoon ontvanger,String password){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendWithAccountTemplate"))
    .queryParam("ontvanger",ontvanger)
    .queryParam("password",password)
;
  restTemplate.put(builder.toUriString(), null);
}


}