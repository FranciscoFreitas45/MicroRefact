package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.LoginService;
public class LoginServiceImpl implements LoginService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public String getEncryptPassword(String passKey,String passVal){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEncryptPassword"))
    .queryParam("passKey",passKey)
    .queryParam("passVal",passVal)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}