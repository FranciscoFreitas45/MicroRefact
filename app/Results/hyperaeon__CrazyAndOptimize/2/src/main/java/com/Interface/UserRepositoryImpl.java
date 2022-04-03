package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.UserRepository;
public class UserRepositoryImpl implements UserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public User getByLoginName(String loginName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getByLoginName"))
    .queryParam("loginName",loginName)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


public User save(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("user",user)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


}