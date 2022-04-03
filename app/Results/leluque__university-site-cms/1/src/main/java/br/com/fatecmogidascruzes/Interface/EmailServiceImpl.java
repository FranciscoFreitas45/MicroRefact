package br.com.fatecmogidascruzes.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.Interface.EmailService;
public class EmailServiceImpl implements EmailService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void sendRequestEMail(String email,String name,String protocol,String requestSituation,String documentType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendRequestEMail"))
    .queryParam("email",email)
    .queryParam("name",name)
    .queryParam("protocol",protocol)
    .queryParam("requestSituation",requestSituation)
    .queryParam("documentType",documentType)
;
  restTemplate.put(builder.toUriString(), null);
}


}