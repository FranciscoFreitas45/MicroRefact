package com.softserve.edu.Resources.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.Interface.User;
public class UserImpl implements User{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public boolean equals(Object obj){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))
    .queryParam("obj",obj)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public int hashCode(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hashCode"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}