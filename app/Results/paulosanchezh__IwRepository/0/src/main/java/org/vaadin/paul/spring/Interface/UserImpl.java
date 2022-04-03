package org.vaadin.paul.spring.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.Interface.User;
public class UserImpl implements User{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Collection<? extends GrantedAuthority> getAuthorities(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAuthorities"))
;  Collection<? extends GrantedAuthority> aux = restTemplate.getForObject(builder.toUriString(), Collection<? extends GrantedAuthority>.class);

 return aux;
}


public Object stream(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/stream"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object anyMatch(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/anyMatch"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}