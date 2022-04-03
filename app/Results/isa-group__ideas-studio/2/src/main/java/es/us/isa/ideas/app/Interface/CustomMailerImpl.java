package es.us.isa.ideas.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.us.isa.ideas.app.Interface.CustomMailer;
public class CustomMailerImpl implements CustomMailer{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Map<String,String> extractCustomizations(Object researcher){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/extractCustomizations"))
    .queryParam("researcher",researcher)
;  Map<String,String> aux = restTemplate.getForObject(builder.toUriString(), Map<String,String>.class);

 return aux;
}


public void sendMail(Map<String,Map<String,String>> recipients,TemplateMail template){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendMail"))
    .queryParam("recipients",recipients)
    .queryParam("template",template)
;
  restTemplate.put(builder.toUriString(), null);
}


}