package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.User;
public class UserImpl implements User{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public int getId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getId"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public String getName(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getName"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}