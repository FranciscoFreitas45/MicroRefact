package br.com.fatecmogidascruzes.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.Interface.ReCaptchaService;
public class ReCaptchaServiceImpl implements ReCaptchaService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public String verifyRecaptcha(String ip,String recaptchaResponse){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/verifyRecaptcha"))
    .queryParam("ip",ip)
    .queryParam("recaptchaResponse",recaptchaResponse)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}