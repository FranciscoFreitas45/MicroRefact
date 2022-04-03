package org.vaadin.paul.spring.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.Interface.User;
public class UserImpl implements User{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public void setBaja(boolean baja){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBaja"))
    .queryParam("baja",baja)
;
  restTemplate.put(builder.toUriString(), null);
}


public String getDni(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDni"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public void setUsername(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUsername"))
    .queryParam("username",username)
;
  restTemplate.put(builder.toUriString(), null);
}


}