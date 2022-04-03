package com.webapp.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.Interface.UserRepository;
public class UserRepositoryImpl implements UserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Optional<User> findByUsername(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUsername"))
    .queryParam("username",username)
;  Optional<User> aux = restTemplate.getForObject(builder.toUriString(), Optional<User>.class);

 return aux;
}


}