package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.User;
public class UserImpl implements User{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Integer getId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getId"))
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


public String getEmail(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmail"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}