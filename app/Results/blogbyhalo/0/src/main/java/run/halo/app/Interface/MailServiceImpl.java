package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.MailService;
public class MailServiceImpl implements MailService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://13";


public void sendTemplateMail(String to,String subject,Map<String,Object> content,String templateName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendTemplateMail"))
    .queryParam("to",to)
    .queryParam("subject",subject)
    .queryParam("content",content)
    .queryParam("templateName",templateName)
;
  restTemplate.put(builder.toUriString(), null);
}


}