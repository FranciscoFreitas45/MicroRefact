package br.com.fatecmogidascruzes.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.Interface.EmailService;
public class EmailServiceImpl implements EmailService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void sendRecoveryPasswordEMail(String to,String hash){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendRecoveryPasswordEMail"))
    .queryParam("to",to)
    .queryParam("hash",hash)
;
  restTemplate.put(builder.toUriString(), null);
}


}