package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.MailService;
public class MailServiceImpl implements MailService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://13";


public void sendTextMail(String to,String subject,String content){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendTextMail"))
    .queryParam("to",to)
    .queryParam("subject",subject)
    .queryParam("content",content)
;
  restTemplate.put(builder.toUriString(), null);
}


}