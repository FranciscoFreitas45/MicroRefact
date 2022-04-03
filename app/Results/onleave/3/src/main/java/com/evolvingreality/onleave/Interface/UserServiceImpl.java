package com.evolvingreality.onleave.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.evolvingreality.onleave.Interface.UserService;
public class UserServiceImpl implements UserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Object get(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}